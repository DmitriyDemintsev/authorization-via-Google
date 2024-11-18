package tech_5dhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import tech_5dhub.exception.UserNotFoundException;
import tech_5dhub.model.User;
import tech_5dhub.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final WebClient webClient;

    @Override
    @Transactional
    public User addUser(User user) {
        log.debug("Записываем юзера в БД");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.debug("Получаем пользователя по id");
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    public void createNewUserAfterOAuthLoginSuccess(String email, String name, String fullName) {
        log.debug("Записываем нового пользователя в БД");
        User user = new User();
        user.setNameFromGoogle(name);
        user.setFullNameFromGoogle(fullName);
        user.setEmailFromGoogle(email);
        user.setEmail(email);
        user.setRole("REGISTERED");
        user.setProvider("GOOGLE");

        userRepository.save(user);
    }

    public void updateNewUserAfterOAuthLoginSuccess(String email, String name, String fullName) {
        log.debug("Обновляем данные уже зарегистрированного пользователя");
        User user = userRepository.getByEmail(email);
        user.setNameFromGoogle(name);
        user.setFullNameFromGoogle(fullName);
        user.setEmailFromGoogle(email);
        user.setProvider("GOOGLE");

        userRepository.save(user);
    }
//
//    public void addCalendarEvents(String email) throws IOException {
//        log.debug("Добавляем события из календаря");
//        List<Event> events = calendarService.getEvents();
//        List<Long> newList = new ArrayList<>();
//        for (Event event: events) {
//            newList.add(Long.valueOf(event.getId()));
//        }
//        User user = userRepository.getByEmail(email);
//        user.setEvent(newList);
//    }


}

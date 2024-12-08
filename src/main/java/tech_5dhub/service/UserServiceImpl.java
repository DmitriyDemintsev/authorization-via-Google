package tech_5dhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech_5dhub.client.dto.CalenderItems;
import tech_5dhub.exception.UserNotFoundException;
import tech_5dhub.model.User;
import tech_5dhub.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CalendarService calendarService;

    @Override
    @Transactional
    public User addUser(User user) {
        log.debug("Record the user in the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.debug("Get the user by id");
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User was not found"));
    }

    public void createNewUserAfterOAuthLoginSuccess(String email, String name, String fullName) {
        log.debug("Record the new user in the database");
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
        log.debug("Updating the data of an already registered user");
        User user = userRepository.getByEmail(email);
        user.setNameFromGoogle(name);
        user.setFullNameFromGoogle(fullName);
        user.setEmailFromGoogle(email);
        user.setProvider("GOOGLE");

        userRepository.save(user);
    }

    @Override
    public List<String> addCalendarEvents(String email) {
        log.debug("Adding events from the calendar");
        List<CalenderItems> events = calendarService.getEventByIdAsync(email);
        List<String> newList = new ArrayList<>();
        for (CalenderItems event: events) {
            newList.add(event.getId());
        }
        User user = userRepository.getByEmail(email);
        user.setEvent(newList);
        user = userRepository.save(user);
        return user.getEvent();
    }
}

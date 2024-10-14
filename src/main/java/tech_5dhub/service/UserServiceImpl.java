package tech_5dhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech_5dhub.exception.UserNotFoundException;
import tech_5dhub.model.User;
import tech_5dhub.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User addUser(User user) {
        log.info("Записываем юзера в БД");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User returnedUser = userRepository.save(user);
        log.info("Это у нас returnedUser: " + returnedUser);
        return returnedUser;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    public void createNewUserAfterOAuthLoginSuccess(String email, String name, String fullName) {
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
        User user = userRepository.getByEmail(email);
        user.setNameFromGoogle(name);
        user.setFullNameFromGoogle(fullName);
        user.setEmailFromGoogle(email);
        user.setProvider("GOOGLE");

        userRepository.save(user);
    }
}

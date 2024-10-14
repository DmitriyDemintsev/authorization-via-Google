package tech_5dhub.service;

import tech_5dhub.model.User;

public interface UserService {

    User addUser(User user);

    User getUserById(Long userId);
}

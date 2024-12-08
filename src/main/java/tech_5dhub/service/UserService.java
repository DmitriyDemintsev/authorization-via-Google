package tech_5dhub.service;

import tech_5dhub.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User getUserById(Long userId);

    List<String> addCalendarEvents(String email);
}

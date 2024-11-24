package tech_5dhub.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech_5dhub.client.dto.CalenderItems;
import tech_5dhub.dto.UserDto;
import tech_5dhub.dto.UserRegistration;
import tech_5dhub.mapper.UserMapper;
import tech_5dhub.model.User;
import tech_5dhub.service.CalendarService;
import tech_5dhub.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @PostMapping("/reg")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserRegistration userRegistration) {
        User user = userMapper.userRegistrationToUser(userRegistration);
        User returnedUser = userService.addUser(user);
        return userMapper.toUserDto(returnedUser);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable @Positive Long userId) {
        return userMapper.toUserDto(userService.getUserById(userId));
    }

    @GetMapping("/loginFailure")
    public String pathFailure() {
        return "Something went wrong";
    }

    @GetMapping("/loginSuccess")
    public String pathSuccess() {
        return "Successful authorization";
    }


}

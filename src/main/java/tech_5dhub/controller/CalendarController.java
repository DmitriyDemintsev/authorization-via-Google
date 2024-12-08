package tech_5dhub.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech_5dhub.service.CurrentUserService;
import tech_5dhub.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/calendar")
@Validated
public class CalendarController {

    private final UserService userService;
    private final CurrentUserService currentUserService;

    @GetMapping("/events")
    public List<String> getGoogleCalendarEvents() {
        final String email = currentUserService.getCurrentUser().getEmailFromGoogle();
        return userService.addCalendarEvents(email);
    }
}

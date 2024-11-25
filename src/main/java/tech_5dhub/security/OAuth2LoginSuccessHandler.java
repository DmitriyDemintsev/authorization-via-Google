package tech_5dhub.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tech_5dhub.controller.UserController;
import tech_5dhub.model.User;
import tech_5dhub.repository.UserRepository;
import tech_5dhub.service.CalendarService;
import tech_5dhub.service.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Component
@AllArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private UserController userController;
    private CalendarService calendarService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        DefaultOidcUser oAuth2User = (DefaultOidcUser) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String name = oAuth2User.getName();
        String fullName = oAuth2User.getFullName();

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            userService.createNewUserAfterOAuthLoginSuccess(email, name, fullName);
        } else {
            userService.updateNewUserAfterOAuthLoginSuccess(email, name, fullName);
        }
    }
}

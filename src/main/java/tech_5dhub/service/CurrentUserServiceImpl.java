package tech_5dhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import tech_5dhub.exception.UnauthorizedException;
import tech_5dhub.model.User;
import tech_5dhub.repository.UserRepository;
import tech_5dhub.security.UserDetailsImpl;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        final var authentication =  SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.debug("Authentication is null");
            throw new UnauthorizedException("An attempt to log in to the application without authorization");
        }
        final var principal = authentication.getPrincipal();
        if (principal == null) {
            log.debug("Principal is null");
            throw new UnauthorizedException("An attempt to log in to the application without authorization");
        }
        if (principal instanceof OidcUser) {
            final String googleEmail = ((OidcUser) principal).getEmail();
            return userRepository.getByEmail(googleEmail);
        } else if (principal instanceof UserDetailsImpl) {
            final var user = ((UserDetailsImpl) principal).getUser();
            log.debug("Got user: {}", user.getEmail());
            return user;
        } else {
            log.debug("Principal is not UserDetailsImpl, but {}", principal);
            throw new UnauthorizedException("An attempt to log in to the application without authorization");
        }
    }
}

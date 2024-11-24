package tech_5dhub.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleAuthenticationException(final JwtAuthenticationException e) {
        log.error("error 401 has occurred UNAUTHORIZED", e);
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleUnauthorizedException(final UnauthorizedException e) {
        log.error("error 401 has occurred UNAUTHORIZED", e);
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException e) {
        log.error("error 404 has occurred NOT_FOUND", e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable e) {
        log.error("An unexpected error has occurred", e);
        return new ErrorResponse("An unexpected error has occurred");
    }
}

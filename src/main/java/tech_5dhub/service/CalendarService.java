package tech_5dhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tech_5dhub.client.CalendarResponse;
import tech_5dhub.client.dto.CalenderItems;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String registrationId;
    private final WebClient webClient;

    public List<CalenderItems> getEventByIdAsync(final String calendarId) {
        log.debug("Получаем id событий из календаря пользователя");
        WebClient.RequestHeadersSpec<?> request = webClient
                .get() //метод для api контроллера (может быть post и т.д)
                .uri("/calendars/" + calendarId + "/events") //продолжение пути/адреса
                .attributes(clientRegistrationId("google"));
        return request // интеграция - передача токена после аутентифиации пользователя
                .retrieve() // отправка запроса
                .bodyToMono(CalendarResponse.class) // указываем, какого класса ответ должен быть
                .block() // заканчиваем запрос – блокируем асинхронный клиент
                .getItems(); // в полученном CalendarResponse забираем айдишники событий
    }
}

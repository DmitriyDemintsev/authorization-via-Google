package tech_5dhub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tech_5dhub.client.CalendarResponse;
import tech_5dhub.client.dto.CalenderItems;

import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import tech_5dhub.config.WebClientConfig;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String registrationId;
    private final WebClient webClient;

    public List<CalenderItems> getEventByIdAsync(final String calendarId) {
        return webClient
                .get()
//                .uri("/" + URLEncoder.encode(calendarId) + "/events")
                .uri("/calendars" + calendarId + "/events")
                .attributes(clientRegistrationId("google"))
                .retrieve()
                .bodyToMono(CalendarResponse.class)
//                .bodyToMono(new ParameterizedTypeReference<List<CalenderItems>>() {})
                .block()
                .getItems();

//        WebClient.RequestHeadersSpec<?> request = webClient
//                .get() //метод для api контроллера (может быть post и т.д)
//                .uri("/" + URLEncoder.encode(calendarId) + "/events") //продолжение пути/адреса
//                .attributes(clientRegistrationId("google"));
//        return request // интеграция - передача токена после аутентифиации пользователя
//                .retrieve() // отправка запроса
//                .bodyToMono(CalendarResponse.class) // указываем, какого класса ответ должен быть
//                .block() // заканчиваем запрос – блокируем асинхронный клиент
//                .getItems(); // в полученном CalendarResponse забираем айдишники событий
    }


//
//            log.debug("получаем события из календаря пользователя");
//        userRepository.findByEmail(email).get().setEvent(calendarService.getEventByIdAsync(email).stream()
//                .map(it -> it.getId())
//            .collect(Collectors.toList()));
//        super.onAuthenticationSuccess(request, response, authentication);

}

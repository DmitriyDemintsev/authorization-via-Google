package tech_5dhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client
        .ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final OAuth2AuthorizedClientManager authorizedClientManager;
    @Value("${google.calendar.base-uri}")
    private String baseUri;

    @Autowired
    public WebClientConfig(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @Bean
    public WebClient webClient() {
        WebClient client = WebClient.builder()
                .baseUrl(this.baseUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .apply(new ServletOAuth2AuthorizedClientExchangeFilterFunction(this.authorizedClientManager)
                        .oauth2Configuration())
                .build();
        return client;
    }
}

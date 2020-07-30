package dev.kurniawan.profile.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
/*
TODO: Evaluate HttpClients: OkHttp, Apache HttpComponent, HttpURLConnection
Also https://stackoverflow.com/a/57252427
 */
public class HttpClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate customOAuth2RestTemplate(TokenProvider tokenProvider) {
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(List.of(
                new HttpNoTokenInterceptor(tokenProvider),
                new HttpExpiredTokenInterceptor(tokenProvider)
        ));
        return restTemplate;
    }
}

package dev.kurniawan.profile.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;

@RequiredArgsConstructor
public class HttpNoTokenInterceptor implements ClientHttpRequestInterceptor {
    private final TokenProvider tokenProvider;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (StringUtils.isEmpty(request.getHeaders().get(HttpHeaders.AUTHORIZATION))) {
            request.getHeaders().setBearerAuth(tokenProvider.getAccessToken());
        }
        return execution.execute(request, body);
    }
}

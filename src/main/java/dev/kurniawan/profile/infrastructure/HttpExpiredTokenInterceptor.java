package dev.kurniawan.profile.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class HttpExpiredTokenInterceptor implements ClientHttpRequestInterceptor {
    private final TokenProvider tokenProvider;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            request.getHeaders().setBearerAuth(tokenProvider.getAccessToken());
            response = execution.execute(request, body);
        }
        return response;
    }
}

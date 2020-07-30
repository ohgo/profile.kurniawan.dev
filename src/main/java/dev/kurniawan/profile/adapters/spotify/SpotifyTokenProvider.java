package dev.kurniawan.profile.adapters.spotify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kurniawan.profile.infrastructure.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SpotifyTokenProvider implements TokenProvider {
    private final RestTemplate restTemplate;
    private final SpotifyProperties properties;

    @Override
    @CachePut(cacheNames = "accessTokens", key = "'spotifyAccessToken'")
    public String refreshAccessToken() {
        /*
        Get access token by refreshing, thus we need to cache the method
        so we aren't calling refresh too much
         */
        String url = String.format("%s/api/token", properties.getToken().getBaseUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(properties.getToken().getUsername(), properties.getToken().getPassword());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LinkedMultiValueMap<String, String> requestBody = convertToMultiValueMap(new RefreshTokenRequestDTO(properties.getToken().getRefresh()));

        ResponseEntity<AccessTokenDTO> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        new HttpEntity(requestBody, headers),
                        AccessTokenDTO.class);

        return response.getBody().getAccess_token();
    }

    @Override
    @Cacheable(cacheNames = "accessTokens", key = "'spotifyAccessToken'")
    public String getAccessToken() {
        return refreshAccessToken();
    }

    private LinkedMultiValueMap<String, String> convertToMultiValueMap(RefreshTokenRequestDTO requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.convertValue(requestBody, new TypeReference<Map<String, String>>() {
        });
        LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();
        map.entrySet().forEach(e -> linkedMultiValueMap.add(e.getKey(), e.getValue()));
        return linkedMultiValueMap;
    }
}

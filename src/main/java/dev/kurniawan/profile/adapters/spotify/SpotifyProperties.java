package dev.kurniawan.profile.adapters.spotify;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties("spotify")
public class SpotifyProperties {
    String baseUrl;
    Token token;
}

@Value
class Token {
    String baseUrl;
    String username;
    String password;
    String refresh;
}

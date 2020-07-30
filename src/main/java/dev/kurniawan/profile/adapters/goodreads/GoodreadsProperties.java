package dev.kurniawan.profile.adapters.goodreads;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties("goodreads")
public class GoodreadsProperties {
    String baseUrl;
    String key;
    String userId;
}

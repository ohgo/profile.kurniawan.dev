package dev.kurniawan.profile.adapters.spotify;

import lombok.Value;

@Value
public class AccessTokenDTO {
    String access_token;
    String scope;
    String expires_in;
    String token_type;
}

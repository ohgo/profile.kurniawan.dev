package dev.kurniawan.profile.adapters.spotify;

import lombok.Value;

@Value
public class RefreshTokenRequestDTO {
    String grant_type = "refresh_token";
    String refresh_token;
}

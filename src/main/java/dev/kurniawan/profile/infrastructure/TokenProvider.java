package dev.kurniawan.profile.infrastructure;

public interface TokenProvider {
    String getAccessToken();

    String refreshAccessToken();
}

package dev.kurniawan.profile.adapters.spotify;

import dev.kurniawan.profile.domains.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SpotifyAdapter {
    private final RestTemplate customOAuth2RestTemplate;
    private final SpotifyProperties properties;

    @Async
    public CompletableFuture<List<Track>> getLastPlayedTracks() {
        String url = String.format("%s/v1/me/player/recently-played", properties.getBaseUrl());
        PlayHistoryResponseDTO response = customOAuth2RestTemplate.getForObject(url, PlayHistoryResponseDTO.class);
        List<Track> tracks = mapToTracks(response);

        return CompletableFuture.completedFuture(tracks);
    }

    private List<Track> mapToTracks(PlayHistoryResponseDTO playHistoryResponseDTO) {
        return playHistoryResponseDTO.getItems()
                .stream()
                .map(playHistoryDTO -> new Track(
                        playHistoryDTO.getTrack().getName(),
                        playHistoryDTO.getTrack().getArtists().get(0).getName(),
                        Instant.parse(playHistoryDTO.getPlayed_at())))
                .collect(Collectors.toList());
    }

}

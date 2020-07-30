package dev.kurniawan.profile.domains;

import dev.kurniawan.profile.adapters.goodreads.GoodreadsAdapter;
import dev.kurniawan.profile.adapters.spotify.SpotifyAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ExternalProfileService implements ProfileService {
    private final SpotifyAdapter spotifyAdapter;
    private final GoodreadsAdapter goodreadsAdapter;

    @Override
    @Cacheable(cacheNames = "profile", key = "'getProfile'")
    public Profile getProfile() {
        CompletableFuture<List<Track>> lastPlayedTracks = spotifyAdapter.getLastPlayedTracks();
        CompletableFuture<List<Book>> currentlyReadBooks = goodreadsAdapter.getCurrentlyReadBooks();

        CompletableFuture.allOf(lastPlayedTracks, currentlyReadBooks).join();

        Profile profile = new Profile(lastPlayedTracks.join(), currentlyReadBooks.join());
        return profile;
    }
}

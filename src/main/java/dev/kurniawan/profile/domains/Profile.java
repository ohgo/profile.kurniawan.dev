package dev.kurniawan.profile.domains;

import lombok.Value;

import java.util.List;

@Value
public class Profile {
    private final List<Track> lastPlayedTracks;
    private final List<Book> currentlyReading;
}

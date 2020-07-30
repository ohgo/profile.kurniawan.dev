package dev.kurniawan.profile.domains;

import lombok.Value;

import java.time.Instant;

@Value
public class Track {
    private final String name;
    private final String artistName;
    private final Instant playedAt;
}

package dev.kurniawan.profile.domains;

import lombok.Value;

import java.time.Instant;

@Value
public class Book {
    private final String isbn;
    private final String isbn13;
    private final String title;
    private final String author;
    private final Instant dateAdded;
}

package dev.kurniawan.profile.adapters.goodreads;

import dev.kurniawan.profile.domains.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoodreadsAdapter {
    private final RestTemplate restTemplate;
    private final GoodreadsProperties properties;

    @Async
    public CompletableFuture<List<Book>> getCurrentlyReadBooks() {
        String url = String.format("%s/review/list/%s.xml?key=%s&v=2&shelf=currently-reading", properties.getBaseUrl(), properties.getUserId(), properties.getKey());
        GoodreadsResponseDTO response = restTemplate.getForObject(url, GoodreadsResponseDTO.class);
        List<Book> books = mapToBooks(response);

        return CompletableFuture.completedFuture(books);
    }

    private List<Book> mapToBooks(GoodreadsResponseDTO goodreadsResponseDTO) {
        return goodreadsResponseDTO.getReviewsRoot().getReviews()
                .stream()
                .map(reviews -> new Book(
                        reviews.getBook().getIsbn(),
                        reviews.getBook().getIsbn13(),
                        reviews.getBook().getTitle_without_series(),
                        reviews.getBook().getAuthors().get(0).getName(),
                        ZonedDateTime.parse(reviews.getDate_added(), DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH)).toInstant()))
                .collect(Collectors.toList());
    }
}

package dev.kurniawan.profile.adapters.goodreads;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Value;

import java.util.List;

@Value
@JacksonXmlRootElement(localName = "GoodreadsResponse")
public class GoodreadsResponseDTO {
    @JacksonXmlProperty(localName = "reviews")
    Reviews reviewsRoot;
}

@Value
class Reviews {
    @JacksonXmlProperty(localName = "review")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Review> reviews;
}

@Value
class Review {
    Book book;
    String date_added;
}

@Value
class Book {
    String isbn;
    String isbn13;
    String title_without_series;
    List<Author> authors;
}

@Value
class Author {
    String name;
}

package dev.kurniawan.profile.adapters.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayHistoryResponseDTO {
    List<playHistoryDTO> items;

}

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
class playHistoryDTO {
    TrackDTO track;
    String played_at;
}

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
class TrackDTO {
    String name;
    List<ArtistDTO> artists;

}

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
class ArtistDTO {
    String name;
}

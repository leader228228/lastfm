package birintsev.lastfm.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Track {
    private String name;
    private Artist artist;
}

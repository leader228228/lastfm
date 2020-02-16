package birintsev.lastfm.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class Band {
    private Collection<Artist> members;
}

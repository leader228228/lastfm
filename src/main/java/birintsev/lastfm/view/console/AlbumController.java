package birintsev.lastfm.view.console;

import birintsev.lastfm.data.Album;
import birintsev.lastfm.restclient.RESTClient;
import birintsev.lastfm.services.AlbumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;
    private static final Logger LOGGER = LogManager.getLogger(AlbumController.class);

    public AlbumController(@Autowired AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping(path = "get", method = RequestMethod.GET)
    public ResponseEntity<Album> getAlbum(
            @RequestParam(name = "artist") @NonNull String artist,
            @RequestParam(name = "albumName") @NonNull String albumName) {
        try {
            Album album = albumService.searchAlbum(artist, albumName);
            return new ResponseEntity<>(album, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error during searching the album '" + albumName + "' artist: " + artist, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

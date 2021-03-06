package birintsev.lastfm.restclient;

import birintsev.lastfm.data.Album;
import birintsev.lastfm.services.AlbumService;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RESTClientImpl implements RESTClient {

    private String username;
    private String password;
    private String email;
    private String APIKey;
    private String sharedSecret;
    private String appName;
    private URL APIRootURL;
    private HttpClient client;
    private AlbumService albumService;
    private static final Logger LOGGER = LogManager.getLogger(RESTClientImpl.class);

    @Override
    public Album searchAlbum(String artistName, String albumName) throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder(APIRootURL.toURI()).GET().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return albumService.parseAlbum(response);
    }
}

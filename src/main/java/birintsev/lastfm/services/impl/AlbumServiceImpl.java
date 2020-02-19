package birintsev.lastfm.services.impl;

import birintsev.lastfm.data.Album;
import birintsev.lastfm.restclient.RESTClient;
import birintsev.lastfm.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Objects;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final RESTClient restClient;

    public AlbumServiceImpl(@Autowired RESTClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Album searchAlbum(String artist, String albumName) throws Exception {
        HttpClient httpClient = restClient.client();
        URI apiRootUrl = restClient.APIRootURL().toURI();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .timeout(Objects.requireNonNullElse(restClient.timeout(), Duration.ofMillis(1000)))
                .uri(new URI(apiRootUrl.getScheme(), apiRootUrl.getAuthority(), "/2.0", "method=album.getinfo&api_key=" + restClient.APIKey() + "&artist=" + artist + "&album=" + albumName, null))
                .build();
        httpClient.send()
    }
}

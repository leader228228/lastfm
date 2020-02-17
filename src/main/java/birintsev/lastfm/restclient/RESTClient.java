package birintsev.lastfm.restclient;

import birintsev.lastfm.data.Album;

import java.net.URL;
import java.net.http.HttpClient;
import java.time.Duration;

public interface RESTClient {
    Album searchAlbum(String artistName, String albumName) throws Exception;
    HttpClient client() throws Exception;
    URL APIRootURL() throws Exception;
    String APIKey();
    Duration timeout();
}

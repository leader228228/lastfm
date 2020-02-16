package birintsev.lastfm.restclient;

import birintsev.lastfm.data.Album;

public interface RESTClient {
    Album searchAlbum(String artistName, String albumName) throws Exception;
}

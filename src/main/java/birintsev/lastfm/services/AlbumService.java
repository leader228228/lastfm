package birintsev.lastfm.services;

import birintsev.lastfm.data.Album;

public interface AlbumService {
    Album searchAlbum(String artist, String albumName) throws Exception;
}

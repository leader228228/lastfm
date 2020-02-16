package birintsev.lastfm.services;

import birintsev.lastfm.data.Album;

import java.net.http.HttpResponse;

public interface AlbumService {
    Album parseAlbum(HttpResponse<String> httpResponse);
}

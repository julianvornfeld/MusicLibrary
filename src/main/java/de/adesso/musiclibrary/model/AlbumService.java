package de.adesso.musiclibrary.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumService {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    private Artist getArtistFromId (Long artistId) {
        return artistRepository.findById(artistId).get();
    }

    public Album createAlbum (String name, LocalDate released, Long artistId) {
        Album album = new Album(name, released, getArtistFromId(artistId));
        albumRepository.save(album);
        return album;
    }

    public void updateArtist (Long albumId, String name, LocalDate released, Long artistId) {
            albumRepository.findById(albumId).ifPresent(album -> {
                album.setName(name);
                album.setReleased(released);
                album.setArtist(getArtistFromId(artistId));
                albumRepository.save(album);
            });
    }
}

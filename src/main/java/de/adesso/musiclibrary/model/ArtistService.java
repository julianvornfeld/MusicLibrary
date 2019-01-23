package de.adesso.musiclibrary.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtistService {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ArtistRepository artistRepository;

    public void createArtist (String name, int founded, Long genreId) {
        genreRepository.findById(genreId).ifPresent(genre -> {
            Artist artist = new Artist(name, founded, genre);
            artistRepository.save(artist);
        });

    }
    public void updateArtist (Long artistId, String name, int founded, Long genreId) {
        genreRepository.findById(genreId).ifPresent(genre -> {
            artistRepository.findById(artistId).ifPresent(artist -> {
                artist.setName(name);
                artist.setFounded(founded);
                artist.setGenre(genre);
                artistRepository.save(artist);
            });
        });
    }
}

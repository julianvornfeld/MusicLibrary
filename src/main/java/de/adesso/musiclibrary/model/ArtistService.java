package de.adesso.musiclibrary.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ArtistService {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ArtistRepository artistRepository;

    private List<Genre> getGenreListFromIds (List<Long> genreIds) {
        List<Genre> genres = new ArrayList<>();

        genreIds.stream().forEach(genreId -> {
            genreRepository.findById(genreId).ifPresent(genre -> {
                genres.add(genre);
            });
        });

        return genres;
    }

    public Artist createArtist (String name, int founded, List<Long> genreIds) {


        Artist artist = new Artist(name, founded, getGenreListFromIds(genreIds));
        artistRepository.save(artist);

        return artist;
    }

    public void updateArtist (Long artistId, String name, int founded, List<Long> genreIds) {
            artistRepository.findById(artistId).ifPresent(artist -> {
                artist.setName(name);
                artist.setFounded(founded);
                artist.setGenre(getGenreListFromIds(genreIds));
                artistRepository.save(artist);
            });
    }
}

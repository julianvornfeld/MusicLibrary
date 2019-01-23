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

    private List<Genre> getGenreList (List<Long> genreIds) {
        List<Genre> genres = new ArrayList<>();

        genreIds.stream().forEach(genreId -> {
            genreRepository.findById(genreId).ifPresent(genre -> {
                genres.add(genre);
            });
        });

        return genres;
    }

    public void createArtist (String name, int founded, List<Long> genreIds) {


        Artist artist = new Artist(name, founded, getGenreList(genreIds));
        artistRepository.save(artist);

    }

    public void updateArtist (Long artistId, String name, int founded, List<Long> genreIds) {
            artistRepository.findById(artistId).ifPresent(artist -> {
                artist.setName(name);
                artist.setFounded(founded);
                artist.setGenre(getGenreList(genreIds));
                artistRepository.save(artist);
            });
    }
}

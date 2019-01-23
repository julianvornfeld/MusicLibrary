package de.adesso.musiclibrary.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistsDataInitializer implements ApplicationRunner {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ArtistService artistService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        genreRepository.save(new Genre("Rock"));
        genreRepository.save(new Genre("Grunge"));

        genreRepository.save(new Genre("Alternative Rock"));
        List<Genre> rockAlternative = genreRepository.findByName("Alternative Rock");

        artistService.createArtist("U2", 1976, rockAlternative.get(0).getId());
        artistService.createArtist("R.E.M.", 1980, rockAlternative.get(0).getId());
        artistService.createArtist("Oasis", 1991, rockAlternative.get(0).getId());
        artistService.createArtist("Foo Fighters", 1994, rockAlternative.get(0).getId());
        artistService.createArtist("Snow Patrol", 1994, rockAlternative.get(0).getId());
    }


}

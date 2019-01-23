package de.adesso.musiclibrary.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ArtistsDataInitializer implements ApplicationRunner {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ArtistService artistService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Genre britpop = new Genre("Britpop");
        Genre grungePost = new Genre("Post-Grunge");
        Genre rock = new Genre("Rock");
        Genre rockHard = new Genre("Hard Rock");
        Genre rockIndie = new Genre("Indie Rock");
        Genre rockAlternative = new Genre("Alternative Rock");

        genreRepository.save(britpop);
        genreRepository.save(grungePost);
        genreRepository.save(rock);
        genreRepository.save(rockHard);
        genreRepository.save(rockIndie);
        genreRepository.save(rockAlternative);

        artistService.createArtist("U2", 1976, Arrays.asList(rock.getId(),rockAlternative.getId()) );
        artistService.createArtist("R.E.M.", 1980, Arrays.asList(rockAlternative.getId()));
        artistService.createArtist("Oasis", 1991, Arrays.asList(britpop.getId(),rockAlternative.getId()));
        artistService.createArtist("Foo Fighters", 1994, Arrays.asList(rockAlternative.getId(), grungePost.getId(), rockHard.getId()));
        artistService.createArtist("Snow Patrol", 1994,  Arrays.asList(rockAlternative.getId(), britpop.getId(), rockIndie.getId()));
    }


}

package de.adesso.musiclibrary.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

//@Component
public class ArtistsDataInitializer implements ApplicationRunner {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private TrackService trackService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Genre britpop = new Genre("Britpop");
        Genre grungePost = new Genre("Post-Grunge");
        Genre rock = new Genre("Rock");
        Genre rockHard = new Genre("Hard Rock");
        Genre rockIndie = new Genre("Indie Rock");
        Genre rockAlternative = new Genre("Alternative Rock");
        Genre folktronica = new Genre("Folktronica");

        genreRepository.save(britpop);
        genreRepository.save(grungePost);
        genreRepository.save(rock);
        genreRepository.save(rockHard);
        genreRepository.save(rockIndie);
        genreRepository.save(rockAlternative);
        genreRepository.save(folktronica);

        Artist u2 = artistService.createArtist("U2", 1976, Arrays.asList(rock.getId(),rockAlternative.getId()) );
        Artist rem = artistService.createArtist("R.E.M.", 1980, Arrays.asList(rockAlternative.getId()));
        Artist oasis = artistService.createArtist("Oasis", 1991, Arrays.asList(britpop.getId(),rockAlternative.getId()));
        Artist fooFighters = artistService.createArtist("Foo Fighters", 1994, Arrays.asList(rockAlternative.getId(), grungePost.getId(), rockHard.getId()));
        Artist snowPatrol = artistService.createArtist("Snow Patrol", 1994,  Arrays.asList(rockAlternative.getId(), britpop.getId(), rockIndie.getId()));
        Artist milkyChance = artistService.createArtist("Milky Chance", 2012,  Arrays.asList(folktronica.getId(), rockAlternative.getId()));

        albumService.createAlbum("Echoes, Silence, Patience & Grace", LocalDate.of(2007, 9, 25), fooFighters.getId());
        Album wildness = albumService.createAlbum("Wildness", LocalDate.of(2018, 5, 25), snowPatrol.getId());
        albumService.createAlbum("Fallen Empires", LocalDate.of(2011, 11, 11), snowPatrol.getId());
        albumService.createAlbum("(What’s the Story) Morning Glory?", LocalDate.of(1995, 9, 29), oasis.getId());
        albumService.createAlbum("Dig Out Your Soul", LocalDate.of(2018, 10, 3), oasis.getId());
        Album blossom = albumService.createAlbum("Blossom", LocalDate.of(2017, 3, 17), milkyChance.getId());

        List<String> wildnessTracks = Arrays.asList(
                "Life on Earth",
                "Don’t Give In",
                "Heal Me",
                "Empress",
                "A Dark Switch",
                "What If This Is All The Love You Ever Get?",
                "A Youth Written In Fire",
                "Soon",
                "Wild Horses",
                "Life And Death");

        List<String> blossomTracks = Arrays.asList(
                "Blossom",
                "Ego",
                "Firebird",
                "Doing Good",
                "Clouds",
                "Cold Blue Rain",
                "Stay",
                "Bad Things",
                "Cocoon",
                "Losing You",
                "Peripeteia",
                "Alive",
                "Piano Song",
                "Heartless");

        trackService.createMultipleTracks(wildnessTracks, wildness.getId());
        trackService.createMultipleTracks(blossomTracks, blossom.getId());

        Track track = trackRepository.findByName("Life on Earth").get(0);
        track.setArtists(Arrays.asList(milkyChance, u2));
        trackRepository.save(track);
    }


}

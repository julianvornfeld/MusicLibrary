package de.adesso.musiclibrary.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({ArtistServiceTest.TestConfiguration.class})
public class ArtistServiceTest {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistService artistService;

    @Before
    public void setUp() throws Exception {
        Genre grungePost = new Genre("Post-Grunge");
        Genre rockHard = new Genre("Hard Rock");
        Genre rockAlternative = new Genre("Alternative Rock");

        genreRepository.save(grungePost);
        genreRepository.save(rockHard);
        genreRepository.save(rockAlternative);

        List<Long> genre = Arrays.asList(rockAlternative.getId(), grungePost.getId(), rockHard.getId());
        artistService.createArtist("Foo Fighters", 1994, genre);
    }

    @Test
    public void createArtist() {
        List<Artist> artists = artistRepository.findByName("Foo Fighters");
        Assert.assertEquals(artists.isEmpty(), false);

        Artist artist = artists.get(0);
        Assert.assertEquals(artist.getName(), "Foo Fighters");
        Assert.assertEquals(artist.getGenre().get(0).getName(), "Alternative Rock");
    }

    public static class TestConfiguration{

        @Bean
        public ArtistService artistService () {
            return new ArtistService();
        }
    }


}
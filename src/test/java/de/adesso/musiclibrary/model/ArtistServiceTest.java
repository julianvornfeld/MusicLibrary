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
        genreRepository.save(new Genre("Alternative Rock"));
        List<Genre> genres = genreRepository.findByName("Alternative Rock");

        artistService.createArtist("Foo Fighters", 1994,genres.get(0).getId());
    }

    @Test
    public void createArtist() {
        List<Artist> artists = artistRepository.findByName("Foo Fighters");
        Assert.assertEquals(artists.isEmpty(), false);

        Artist artist = artists.get(0);
        Assert.assertEquals(artist.getName(), "Foo Fighters");
        Assert.assertEquals(artist.getGenre().getName(), "Alternative Rock");
    }

    public static class TestConfiguration{

        @Bean
        public ArtistService artistService () {
            return new ArtistService();
        }
    }


}
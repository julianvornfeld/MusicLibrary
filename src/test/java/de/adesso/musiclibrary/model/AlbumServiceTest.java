package de.adesso.musiclibrary.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({AlbumServiceTest.TestConfiguration.class})
public class AlbumServiceTest {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Test
    public void createAlbum() {
        List<Artist> artists = artistRepository.findByName("Foo Fighters");
        Assert.assertEquals(artists.isEmpty(), false);

        Artist artist = artists.get(0);

        List<Album> albums = albumRepository.findByNameAndArtist("Echoes, Silence, Patience & Grace", artist);

        Assert.assertEquals(albums.get(0).getArtist().getName(), "Foo Fighters");
        Assert.assertEquals(albums.get(0).getName(), "Echoes, Silence, Patience & Grace");
    }

    public static class TestConfiguration{

        @Bean
        public AlbumService albumService() {
            return new AlbumService();
        }

        @Bean
        public ArtistService artistService () {
            return new ArtistService();
        }

        @Bean
        public ArtistsDataInitializer DataService () {
            return new ArtistsDataInitializer();
        }
    }


}
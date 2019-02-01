package de.adesso.musiclibrary.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreTest {

    @Autowired
    private GenreRepository genreRepository;

    @Before
    public void setUp() throws Exception {
        genreRepository.save(new Genre("Rock"));
    }

    @Test
    public void createTest() {
        List<Genre> genre = genreRepository.findByName("Rock");
        Assert.assertEquals(genre.isEmpty(), false);
    }



}
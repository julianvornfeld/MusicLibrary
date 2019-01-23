package de.adesso.musiclibrary.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int founded;

    @ManyToMany
    private List<Genre> genre;

    protected Artist() {
    }

    public Artist(String name, int founded, List<Genre> genre) {
        this.name = name;
        this.founded = founded;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public String getGenreString() {
        StringBuilder genreString = new StringBuilder();

        genre.stream().forEach(genre -> {
            genreString.append(genre.getName() + ", ");
        });

        return genreString.toString().trim();
    }

    public boolean isGenreInArtist(Genre genreToCheck) {
        if (genre == null) {
            return false;
        } else {
            return genre.contains(genreToCheck);
        }
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return String.format(
                "Artist[id=%d, firstName='%s', lastName='%s']",
                id, name, founded, genre);
    }

}
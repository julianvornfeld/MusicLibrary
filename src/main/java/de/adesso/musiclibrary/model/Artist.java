package de.adesso.musiclibrary.model;

import javax.persistence.*;

@Entity
public class Artist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int founded;

    @ManyToOne
    private Genre genre;

    protected Artist() {
    }

    public Artist(String name, int founded, Genre genre) {
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return String.format(
                "Artist[id=%d, firstName='%s', lastName='%s']",
                id, name, founded, genre);
    }

}
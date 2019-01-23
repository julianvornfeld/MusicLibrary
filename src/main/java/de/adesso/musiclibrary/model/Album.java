package de.adesso.musiclibrary.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate released;

    @ManyToOne
    private Artist artist;

    protected Album() {
    }

    public Album(String name, LocalDate released, Artist artist) {
        this.name = name;
        this.released = released;
        this.artist = artist;
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

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

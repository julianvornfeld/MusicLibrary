package de.adesso.musiclibrary.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Track {

    @Id
    @GeneratedValue
    private Long id;
    private int nr;
    private String name;

    @ManyToMany
    private List<Artist> artists;

    @ManyToOne
    private Album album;

    protected Track() {
    }

    public Track(int nr, String name, List<Artist> artists, Album album) {
        this.nr = nr;
        this.name = name;
        this.artists = artists;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getArtistString() {

        if (artists != null) {
            StringBuilder artistString = new StringBuilder();

            artists.stream().forEach(artist -> {
                artistString.append(artist.getName() + ", ");
            });

            return artistString.toString().trim();
        } else {
            return "";
        }
    }

}

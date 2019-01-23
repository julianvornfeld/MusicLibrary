package de.adesso.musiclibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Override
    public String toString() {
        return String.format(
                "Artist[id=%d, firstName='%s', lastName='%s']",
                id, name);
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Genre() {
    }


}
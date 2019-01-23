package de.adesso.musiclibrary.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findByName(String name);
    List<Album> findByNameAndArtist(String name, Artist artist);
    List<Album> findByArtist(Artist artist);
}
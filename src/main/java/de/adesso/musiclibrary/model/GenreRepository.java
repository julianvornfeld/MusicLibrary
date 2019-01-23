package de.adesso.musiclibrary.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findByName(String name);
}
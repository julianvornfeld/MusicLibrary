package de.adesso.musiclibrary.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findByName(String name);

    @Query("select g from Genre g order by name")
    Iterable<Genre> findAllOrderByName();
}
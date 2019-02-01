package de.adesso.musiclibrary.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

    List<Artist> findByName(String name);

    @Query("select a from Artist a order by name")
    Iterable<Artist> findAllOrderByName();
}
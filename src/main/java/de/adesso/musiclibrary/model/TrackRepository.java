package de.adesso.musiclibrary.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Long> {

    List<Track> findByName(String name);
    Long countByAlbum(Album album);
    List<Track> findByAlbum_IdOrderByNr(Long albumId);

    @Query ("select t from Track t order by nr")
    Iterable<Track> findAllOrderByNr();
}
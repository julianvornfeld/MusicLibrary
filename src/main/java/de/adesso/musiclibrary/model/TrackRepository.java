package de.adesso.musiclibrary.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Long> {

    List<Track> findByName(String name);
    Long countByAlbum(Album album);
    List<Track> findByAlbum_Id(Long albumId);
}
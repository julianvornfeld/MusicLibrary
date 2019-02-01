package de.adesso.musiclibrary.model;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.IntBuffer;
import java.util.*;

@Component
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    private Album getAlbumFromId (Long albumId) {
        return albumRepository.findById(albumId).get();
    }

    private List<Artist> getArtistListFromIds (List<Long> artistIds) {
        if (artistIds!=null) {
            List<Artist> artists = new ArrayList<>();

            artistIds.stream().forEach(artistId -> {
                artistRepository.findById(artistId).ifPresent(artist -> {
                    artists.add(artist);
                });
            });

            return artists;
        } else {
            return null;
        }

    }

    public Track createTrack (int nr, String name, List<Long> artistIds, Long albumId) {

        Track track = new Track(nr, name, getArtistListFromIds(artistIds), getAlbumFromId(albumId));
        trackRepository.save(track);

        return track;
    }

    public Track updateTrack (Long trackId, int nr, String name, List<Long> artistIds) {

        Track track = trackRepository.findById(trackId).get();
        track.setNr(nr);
        track.setName(name);
        track.setArtists(getArtistListFromIds(artistIds));

        trackRepository.save(track);

        return track;
    }

    public void createMultipleTracks (List<String> names, Long albumId) {

        int nr = 1;
        Album album = getAlbumFromId(albumId);

        for (int i = 0; i < names.size(); i++) {
            Track track = new Track(nr, names.get(i), null, album);
            trackRepository.save(track);
            nr++;
        }

    }

    public void renumber (Long albumId) {
        List<Track> tracks = trackRepository.findByAlbum_IdOrderByNr(albumId);

        tracks.sort(Comparator.comparingDouble(Track::getNr));

        int nr = 1;
        for (int i = 0; i < tracks.size(); i++) {
            tracks.get(i).setNr(nr);
            nr++;
        }
    }

}

package de.adesso.musiclibrary.model;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    public void createMultipleTracks (List<String> names, Long albumId) {

        int nr = 1;
        Album album = getAlbumFromId(albumId);
        //List<Artist> artists =  Arrays.asList(album.getArtist());

        for (int i = 0; i < names.size(); i++) {
            Track track = new Track(nr, names.get(i), null, album);
            trackRepository.save(track);
            nr++;
        }

    }

    public void renumber (Long albumId) {
        List<Track> tracks = trackRepository.findByAlbum_Id(albumId);

        tracks.sort(Comparator.comparingDouble(Track::getNr));

        int nr = 1;
        for (int i = 0; i < tracks.size(); i++) {
            tracks.get(i).setNr(nr);
            nr++;
        }
    }

}

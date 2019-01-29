package de.adesso.musiclibrary.controller;

import de.adesso.musiclibrary.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private TrackService trackService;

    private Model prepareTrackModel(Model model, Long albumId) {

        Optional<Album> album = albumRepository.findById(albumId);
        Iterable<Track> tracks = trackRepository.findAll();

        model.addAttribute("album", album.get());
        model.addAttribute("tracks", tracks);
        model.addAttribute("nextTrackNr", trackRepository.countByAlbum(album.get()) + 1);

        return model;
    }

    @RequestMapping(value = { "/tracks" }, method = RequestMethod.GET)
    public String viewTrackList(Model model) {
        Iterable<Track> tracks = trackRepository.findAll();
        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("tracks", tracks);
        model.addAttribute("albums", albums);

        model.addAttribute("activepage", "tracks");
        return "tracks";
    }

    @RequestMapping(value = "/tracks/edit/{id}", method = RequestMethod.POST)
    public String getEditTracks(Model model, @RequestParam Long albumId){

        prepareTrackModel(model, albumId);

        model.addAttribute("activepage", "tracks");
        return "trackedit";
    }

    @RequestMapping(value = "/tracks/save/{id}", method = RequestMethod.POST)
    public String saveTracks(Model model, @RequestParam Long albumId, @RequestParam int nr, @RequestParam String name){

        trackService.createTrack(nr, name, null, albumId);

        prepareTrackModel(model, albumId);

        model.addAttribute("activepage", "tracks");
        return "trackedit";
    }

    @RequestMapping(value = "/tracks/delete/{id}", method = RequestMethod.POST)
    public String deleteTrack(Model model, @RequestParam Long albumId, @RequestParam Long trackId){

        trackRepository.deleteById(trackId);
        trackService.renumber(albumId);

        prepareTrackModel(model, albumId);

        model.addAttribute("activepage", "tracks");
        return "trackedit";
    }
}

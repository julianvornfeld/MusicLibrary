package de.adesso.musiclibrary.controller;

import de.adesso.musiclibrary.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumService albumService;


    @RequestMapping(value = { "/albums" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);

        return "albums";
    }

    @RequestMapping(value = { "/albums/new" }, method = RequestMethod.GET)
    public String getCreateAlbum(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("mode", "New");

        return "albumedit";
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.GET)
    public String getEditAlbum(@PathVariable("id") Long id, Model model){
        Optional<Album> album = albumRepository.findById(id);
        Iterable<Artist> artists = artistRepository.findAll();

        model.addAttribute("album", album.get());
        model.addAttribute("artists", artists);
        model.addAttribute("mode", "Edit");

        return "albumedit";
    }

    @RequestMapping(value = { "/albums/edit" }, method = RequestMethod.POST)
    public String postCreateAlbum(Model model, @RequestParam Long albumId, @RequestParam String name, @RequestParam String released, @RequestParam Long artistId, @RequestParam String mode) {

        LocalDate releasedLocalDate = LocalDate.parse(released);

        mode = mode.toLowerCase();
        if (mode.equals("new")) {
            albumService.createAlbum(name, releasedLocalDate, artistId);
        } else if (mode.equals("edit")) {
            albumService.updateArtist(albumId, name, releasedLocalDate, artistId);
        }
        return "redirect:/albums";

    }

}

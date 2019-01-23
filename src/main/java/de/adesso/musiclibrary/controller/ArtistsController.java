package de.adesso.musiclibrary.controller;

import de.adesso.musiclibrary.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ArtistsController {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ArtistService artistService;


    @RequestMapping(value = { "/artists" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);

        return "artists";
    }

    @RequestMapping(value = { "/artists/new" }, method = RequestMethod.GET)
    public String getCreateArtist(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("mode", "New");

        return "artistedit";
    }

    @RequestMapping(value = "/artists/{id}", method = RequestMethod.GET)
    public String getEditGenre(@PathVariable("id") Long id, Model model){
        Optional<Artist> artist = artistRepository.findById(id);
        Iterable<Genre> genres = genreRepository.findAll();

        model.addAttribute("artist", artist.get());
        model.addAttribute("genres", genres);
        model.addAttribute("mode", "Edit");

        return "artistedit";
    }

    @RequestMapping(value = { "/artists/edit" }, method = RequestMethod.POST)
    public String postCreateArtist(Model model, @RequestParam Long artistId, @RequestParam String name, @RequestParam int founded, @RequestParam Long genreId, @RequestParam String mode) {

        mode = mode.toLowerCase();

        if (mode.equals("new")) {
            artistService.createArtist(name, founded, genreId);
        } else if (mode.equals("edit")) {
            artistService.updateArtist(artistId,name,founded,genreId);
        }
        return "redirect:/artists";

    }

}
package de.adesso.musiclibrary.controller;

import de.adesso.musiclibrary.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
        Iterable<Artist> artists = artistRepository.findAllOrderByName();
        model.addAttribute("artists", artists);

        model.addAttribute("activepage", "artists");
        return "artists";
    }

    @RequestMapping(value = { "/artists/new" }, method = RequestMethod.GET)
    public String getCreateArtist(Model model) {
        Iterable<Genre> genres = genreRepository.findAllOrderByName();
        model.addAttribute("genres", genres);
        model.addAttribute("mode", "New");

        model.addAttribute("activepage", "artists");
        return "artistedit";
    }

    @RequestMapping(value = "/artists/{id}", method = RequestMethod.GET)
    public String getEditAlbum(@PathVariable("id") Long id, Model model){
        Optional<Artist> artist = artistRepository.findById(id);
        Iterable<Genre> genres = genreRepository.findAllOrderByName();

        model.addAttribute("artist", artist.get());
        model.addAttribute("genres", genres);
        model.addAttribute("mode", "Edit");

        model.addAttribute("activepage", "artists");
        return "artistedit";
    }

    @RequestMapping(value = { "/artists/edit" }, method = RequestMethod.POST)
    public String postCreateArtist(Model model, @RequestParam Long artistId, @RequestParam String name, @RequestParam int founded, @RequestParam List<Long> genreIds, @RequestParam String mode) {

        mode = mode.toLowerCase();
        if (mode.equals("new")) {
            artistService.createArtist(name, founded, genreIds);
        } else if (mode.equals("edit")) {
            artistService.updateArtist(artistId,name,founded,genreIds);
        }

        model.addAttribute("activepage", "artists");
        return "redirect:/artists";

    }

}

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
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @RequestMapping(value = { "/genres" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        model.addAttribute("activepage", "genres");
        return "genres";
    }

    @RequestMapping(value = { "/genres/new" }, method = RequestMethod.GET)
    public String getCreateArtist(Model model) {
        model.addAttribute("mode", "New");

        model.addAttribute("activepage", "genres");
        return "genreedit";
    }

    @RequestMapping(value = "/genres/{id}", method = RequestMethod.GET)
    public String getEditGenre(@PathVariable("id") Long id, Model model){
        Optional<Genre> genres = genreRepository.findById(id);
        model.addAttribute("genre", genres.get());
        model.addAttribute("mode", "Edit");

        model.addAttribute("activepage", "genres");
        return "genreedit";
    }

    @RequestMapping(value = "/genres/edit", method = RequestMethod.POST)
    public String postEditGenre(@RequestParam String name, @RequestParam Long genreId, @RequestParam String mode, Model model){
        mode = mode.toLowerCase();

        if (mode.equals("new")) {
            genreRepository.save(new Genre(name));
        } else if (mode.equals("edit")) {
            Optional<Genre> genres = genreRepository.findById(genreId);
            genres.get().setName(name);
            genreRepository.save(genres.get());
        }

        model.addAttribute("activepage", "genres");
        return "redirect:/genres";
    }

}

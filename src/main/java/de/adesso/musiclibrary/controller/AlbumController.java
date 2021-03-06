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
import java.util.stream.StreamSupport;

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
        Iterable<Artist> artists = artistRepository.findAll();
        Iterable<Album> albums = albumRepository.findAllOrderByNr();

        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);

        model.addAttribute("activepage", "albums");
        return "albums";
    }

    @RequestMapping(value = { "/albums/new" }, method = RequestMethod.GET)
    public String getCreateAlbum(@RequestParam Long ArtistId, Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("ArtistId", ArtistId);
        model.addAttribute("mode", "New");

        model.addAttribute("activepage", "albums");
        return "albumedit";
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.GET)
    public String getEditAlbum(@PathVariable("id") Long id, Model model){
        Optional<Album> album = albumRepository.findById(id);
        Iterable<Artist> artists = artistRepository.findAll();

        model.addAttribute("album", album.get());
        model.addAttribute("artists", artists);
        model.addAttribute("mode", "Edit");

        model.addAttribute("activepage", "albums");
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
        model.addAttribute("activepage", "albums");
        return "redirect:/albums";

    }
    @RequestMapping(value = { "/albums/filter" }, method = RequestMethod.GET)
    public String GetFilterAlbum(Model model, @RequestParam Long artistId) {

        Iterable<Artist> artists = artistRepository.findAll();
        Iterable<Album> albums = null;
        if (artistId >= 0) {
            Artist artist = StreamSupport.stream(artists.spliterator(), false)
                    .filter(artist1 -> artist1.getId().equals(artistId))
                    .findFirst().get();

            albums = albumRepository.findByArtistOrderByReleased(artist);

            model.addAttribute("ArtistId", artist.getId());
            model.addAttribute("ArtistName", artist.getName());
        } else if (artistId == -1){

            albums = albumRepository.findAll();
        }

        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);

        model.addAttribute("activepage", "albums");
        return "albums";
    }

}

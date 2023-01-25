package com.project38.pubtalkapp.controller;

import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Artist>> getArtists() {
        List<Artist> artists = artistService.findAllArtists();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping("/find{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable("id") Long id) {
        Artist artist = artistService.findArtistById(id);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Artist> addArtist(@RequestBody Artist newArtist) {
        Artist createdArtist = artistService.createArtist(newArtist);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Artist> updateArtist(@RequestBody Artist artist) {
        Artist editedArtist = artistService.editArtist(artist);
        return new ResponseEntity<>(editedArtist, HttpStatus.OK);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> removeArtist(@PathVariable("id") Long id) {
        artistService.deleteArtistById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.repo.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private ArtistRepo artistRepo;

    @Autowired
    public ArtistService(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }

    public List<Artist> findAllArtists() {
        return null;
    }

    public Artist findArtistById(Long id) {
        return null;
    }

    public Artist createArtist(Artist artist) {
        return null;
    }

    public Artist editArtist(Artist artist) {
        return null;
    }

    public void deleteArtistById(Long id) {

    }
}

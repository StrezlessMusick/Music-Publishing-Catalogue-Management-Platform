package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.exception.ArtistNotFoundException;
import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.repo.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepo artistRepo;

    @Autowired
    public ArtistService(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }

    public List<Artist> findAllArtists() {
        return artistRepo.findAll();
    }

    public Artist findArtistById(Long id) {
        return artistRepo.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(
                        String.format("Artist with id [%s] not found.", id)
                ));
    }

    public Artist createArtist(Artist newArtist) {
        return artistRepo.save(newArtist);
    }

    public Artist editArtist(Artist artist) {
        Optional<Artist> existingArtist = artistRepo.findById(artist.getId());
        if (existingArtist.isPresent())
            return artistRepo.save(artist);

        return artist;
    }

    public void deleteArtistById(Long id) {
        artistRepo.deleteById(id);
    }
}

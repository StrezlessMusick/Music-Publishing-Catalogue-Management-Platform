package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.exception.ArtistNotFoundException;
import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.ArtistRepo;
import com.project38.pubtalkapp.repo.TrackRepo;
import java.util.Collections;

import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    public ArtistService(ArtistRepo artistRepo, TrackRepo trackRepo) {
        this.artistRepo = artistRepo;
        this.trackRepo = trackRepo;
    }

    public List<Artist> findAllArtists() {
        return artistRepo.findAll();
    }

    public List<Track> findAllTracksAssociatedWithArtistByID(Long id) {
        Artist artistOpt = findArtistById(id);
        List<Track> trackList = artistOpt.getArtistTracks();

        // find the tracks associated with artist
        List<Track> all = trackRepo.findAll();
        List<Track> artistTracks = trackRepo.findAllTracksByArtistID(id);

        // add any found tracks to trackList
        trackList.addAll(artistTracks);

        return Collections.emptyList();
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
        return artistRepo.save(artist);
    }

    public void deleteArtistById(Long id) {
        artistRepo.deleteById(id);
    }



    private final ArtistRepo artistRepo;
    private final TrackRepo trackRepo;
}

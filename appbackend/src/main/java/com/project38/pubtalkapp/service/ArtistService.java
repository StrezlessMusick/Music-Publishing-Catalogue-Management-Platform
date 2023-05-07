package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.exception.ArtistNotFoundException;
import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.ArtistRepo;
import com.project38.pubtalkapp.repo.TrackRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArtistService {

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
        log.info("\ncurrent tracklist:\n " + trackList);

        // find the tracks associated with artist
        List<Track> all = trackRepo.findAll();
        log.info("\nList of all tracks: \n" + all);

        List<Track> artistTracks = trackRepo.findAllTracksByArtistID(id);
        log.info("\ntracks associated with artist: \n" + all);

        log.info("\nadding any found tracks to trackList...\n");
        List<Track> updatedTrackList = trackList;
        trackList.addAll(artistTracks);

        for (Track t : artistTracks) {
            updatedTrackList.add(t);
        }
        log.info("\nupdated tracklist: \n" + updatedTrackList);

        return trackList;
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

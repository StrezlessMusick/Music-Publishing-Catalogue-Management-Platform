package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.exception.TrackNotFoundException;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.TrackRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrackService {


    public List<Track> findAllTracks() {
        return trackRepo.findAll();
    }

    public Track findTrackById(Long id) {
        return trackRepo.findById(id)
                .orElseThrow(() -> new TrackNotFoundException(
                        String.format("Track with id [%s] not found.", id)
                ));
    }

    public Track createTrack(Track track) {
        return trackRepo.save(track);
    }

    public Track editTrack(Track track) {
        return trackRepo.save(track);
    }

    public void deleteTrackById(Long id) {
        trackRepo.deleteById(id);
    }

    public TrackService(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

    private final TrackRepo trackRepo;
}

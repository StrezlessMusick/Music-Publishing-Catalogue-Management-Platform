package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    private TrackRepo trackRepo;

    @Autowired
    public TrackService(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

    public List<Track> findAllTracks() {
        return null;
    }

    public Track findTrackById(Long id) {
        return null;
    }

    public Track createTrack(Track track) {
        return null;
    }

    public Track editTrack(Track track) {
        return null;
    }

    public void deleteTrackById(Long id) {

    }
}

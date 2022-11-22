package com.project38.pubtalkapp.controller;

import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/tracks")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Track>> getTracks() {
        List<Track> allTracks = trackService.findAllTracks();
        return new ResponseEntity<>(allTracks, HttpStatus.OK);
    }

    @GetMapping("/find{id}")
    public ResponseEntity<Track> getTrack(@PathVariable("id") Long id) {
        Track trackById = trackService.findTrackById(id);
        return new ResponseEntity<>(trackById, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Track> addTrack(@RequestBody Track newTrack) {
        Track createdTrack = trackService.createTrack(newTrack);
        return new ResponseEntity<>(createdTrack, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Track> updateTrack(@RequestBody Track track) {
        Track editedTrack = trackService.editTrack(track);
        return new ResponseEntity<>(editedTrack, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> removeTrack(@PathVariable("id") Long id) {
        trackService.deleteTrackById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

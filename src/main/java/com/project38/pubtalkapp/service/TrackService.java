package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.repo.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    private TrackRepo trackRepo;

    @Autowired
    public TrackService(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

}

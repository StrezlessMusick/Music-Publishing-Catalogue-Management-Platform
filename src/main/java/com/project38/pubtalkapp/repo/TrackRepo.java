package com.project38.pubtalkapp.repo;

import com.project38.pubtalkapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends JpaRepository<Track, Long> {
}

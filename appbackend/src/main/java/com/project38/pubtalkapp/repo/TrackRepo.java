package com.project38.pubtalkapp.repo;

import com.project38.pubtalkapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepo extends JpaRepository<Track, Long> {


    @Query(
            value = "SELECT * " +
                    "FROM tracks t " +
                    "LEFT JOIN artist_track a " +
                    "ON t.id = a.track_id",
            nativeQuery = true
    )
    public List<Track> findAllTracksAndFetchArtists();

    @Query(
            value = "SELECT track_name " +
                    "FROM tracks t " +
                    "JOIN artist_track a " +
                    "on t.id = a.track_id " +
                    "WHERE a.artist_id = ?",
            nativeQuery = true
    )
    List<Track> findAllTracksByArtistID(@Param("a.artist_id") Long id);

}

package com.project38.pubtalkapp.repo;

import com.project38.pubtalkapp.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {

    @Query(
            value = "SELECT * FROM artists LEFT JOIN artist_track a on artists.id = a.artist_id",
            nativeQuery = true
    )
    public List<Artist> findAllArtistAndFetchTracks();
}

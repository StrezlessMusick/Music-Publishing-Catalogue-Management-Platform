package com.project38.pubtalkapp.repo;

import com.project38.pubtalkapp.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {

    @Query(
            value = "SELECT a FROM artists a " +
                    "LEFT JOIN artist_track t " +
                    "on a.id = t.artist_id",
            nativeQuery = true
    )
    public List<Artist> findAllArtistAndFetchTracks();
}

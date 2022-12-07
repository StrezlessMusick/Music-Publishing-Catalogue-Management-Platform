package com.project38.pubtalkapp.repo;

import com.project38.pubtalkapp.model.Artist;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {

    @Modifying
    @Query(value = "UPDATE artist " +
            "SET artist_name = ?," +
            "artist_image_url = ?," +
            "pro = ?," +
            "proipi = ? " +
            "WHERE id = ?")
    Artist updateArtist(Artist artist);
}

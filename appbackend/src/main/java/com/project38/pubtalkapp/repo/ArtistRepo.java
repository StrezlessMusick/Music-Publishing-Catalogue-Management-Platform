package com.project38.pubtalkapp.repo;

import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {

   
}

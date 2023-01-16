package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.PRO;
import com.project38.pubtalkapp.model.Project;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.ArtistRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

class ArtistServiceTest {

    @Mock
    private ArtistRepo artistRepo;
    private ArtistService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new ArtistService(artistRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldFindAllArtists() {
        // Given
        List<Track> trackList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        List<Artist> artistList = List.of(
                new Artist(
                        1L,
                        "Billy",
                        "www.imageurl.com",
                        PRO.ASCAP,
                        "22321",
                        trackList,
                        projectList
                ),
                new Artist(
                        2L,
                        "Tyler",
                        "www.imageurl2.com",
                        PRO.BMI,
                        "53627",
                        trackList,
                        projectList
                )
        );

        // When
        doReturn(artistList).when(artistRepo).findAll();

        // Then
        List<Artist> testList = underTest.findAllArtists();
        assertThat(testList).hasSize(2);
    }

    @Test
    void itShouldFindArtistById() {
        // Given
        List<Track> trackList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        Long id = 1L;
        Artist billy = new Artist(
                id,
                "Billy",
                "www.imageurl.com",
                PRO.ASCAP,
                "22321",
                trackList,
                projectList
        );
        
        artistRepo.saveAndFlush(billy);

        // When
        Artist returned = underTest.findArtistById(id);

        // Then
        assertThat(billy).isEqualToComparingFieldByField(returned);

    }

    @Test
    void itShouldCreateArtist() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldEditArtist() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldDeleteArtistById() {
        // Given

        // When

        // Then

    }
}

package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.exception.ArtistNotFoundException;
import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.PRO;
import com.project38.pubtalkapp.model.Project;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.ArtistRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepo artistRepo;
    @InjectMocks
    private ArtistService underTest;

    @BeforeEach
    void setUp() {
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

        Optional<Artist> artistOptional = Optional.of(billy);
        when(artistRepo.findById(id)).thenReturn(artistOptional);

        // When
        Artist returned = underTest.findArtistById(id);

        // Then
        assertThat(billy).isEqualToComparingFieldByField(returned);

    }

    @Test
    void itShouldThrowArtistNotFoundException() {
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

        Optional<Artist> artistOptional = Optional.of(billy);
        when(artistRepo.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ArtistNotFoundException.class, () -> underTest.findArtistById(id));

    }

    @Test
    void itShouldCreateArtist() {
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
        when(artistRepo.save(billy)).thenReturn(billy);

        // When
        Artist returned = underTest.createArtist(billy);

        // Then
        assertThat(billy).isEqualToComparingFieldByField(returned);

        try {
            underTest.findArtistById(id);
        } catch (ArtistNotFoundException e) {
            assertEquals(String.format("Artist with id [%s] not found.", id),
                    e.getMessage());
        }

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

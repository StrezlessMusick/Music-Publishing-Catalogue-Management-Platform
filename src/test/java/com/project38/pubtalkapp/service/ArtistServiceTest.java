package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.exception.ArtistNotFoundException;
import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.PRO;
import com.project38.pubtalkapp.model.Project;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.ArtistRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepo artistRepo;
    @InjectMocks
    private ArtistService underTest;

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
        when(artistRepo.findAll()).thenReturn(artistList);

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
        Optional<Artist> artistOpt = Optional.of(billy);
        when(artistRepo.findById(id)).thenReturn(artistOpt);

        // When & Then
        assertEquals(artistOpt.get().getArtistName(), artistRepo.findById(id).get().getArtistName());
        assertThrows(ArtistNotFoundException.class, () -> underTest.findArtistById(2L));

        Long tcId = 5L;
        try {
            underTest.findArtistById(tcId);
        } catch (ArtistNotFoundException e) {
            assertEquals(String.format("Artist with id [%s] not found.", tcId),
                    e.getMessage());
        }
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
    }

    @Test
    void itShouldEditExistingArtist() {
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
        artistRepo.save(billy);
        when(artistRepo.findById(id)).thenReturn(Optional.of(billy));

        // When
        Artist jackie = new Artist(
                id,
                "Jackie",
                "www.imageurl.com",
                PRO.ASCAP,
                "22328",
                trackList,
                projectList
        );
        underTest.editArtist(jackie);
        when(artistRepo.findById(id)).thenReturn(Optional.of(jackie));

        // Then
        verify(artistRepo).save(billy);
        assertEquals("Jackie", artistRepo.findById(id).get().getArtistName());

        assertNotEquals(billy, jackie);
        assertNotEquals("Billy", underTest.findArtistById(id).getArtistName());
        assertNotEquals("22321", artistRepo.findById(id).get().getProIPI());
    }

    @Test
    void itShouldDeleteArtistById() {
        // Given
        List<Track> trackList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        Artist billy = new Artist(
                1L,
                "Billy",
                "www.imageurl.com",
                PRO.ASCAP,
                "22321",
                trackList,
                projectList
        );
        Artist jackie = new Artist(
                2L,
                "Jackie",
                "www.imageurl.com",
                PRO.ASCAP,
                "22321",
                trackList,
                projectList
        );
        artistRepo.saveAll(Arrays.asList(billy, jackie));

        when(artistRepo.findById(1L)).thenReturn(Optional.empty());
        when(artistRepo.findById(2L)).thenReturn(Optional.of(jackie));

        // When
        underTest.deleteArtistById(1L);

        // Then
        verify(artistRepo, times(1)).deleteById(1L);

        assertEquals(Optional.empty(), artistRepo.findById(1L));
        assertEquals("Jackie", artistRepo.findById(2L).get().getArtistName());
    }

}

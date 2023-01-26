package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.exception.ArtistNotFoundException;
import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.PRO;
import com.project38.pubtalkapp.model.Project;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.ArtistRepo;
import com.project38.pubtalkapp.repo.TrackRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepo artistRepo;
    @Mock
    private TrackRepo trackRepo;
    @InjectMocks
    private ArtistService underTest;

    @Test
    void itShouldFindAllArtists() {
        // Given
        List<Track> trackList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();

        List<Artist> artistList = Arrays.asList(
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
    void itShouldFindAllArtistsAndAssociatedTracks() {
        // Given
        List<Artist> testList = new ArrayList<>();
        List<Track> trackList = Arrays.asList(
                new Track(
                        1L,
                        "Straight Fire",
                        "www.imageUrl.com",
                        "www.trackUrl.com",
                        321,
                        testList,
                        null

                ),
                new Track(
                        2L,
                        "Better Than Ever",
                        "www.imageUrl.com",
                        "www.trackUrl.com",
                        321,
                        testList,
                        null
                )
        );
        List<Project> projectList = new ArrayList<>();

        List<Artist> artistList = Arrays.asList(
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
        when(artistRepo.findAllArtistAndFetchTracks()).thenReturn(artistList);

        // Then
        List<Artist> returned = underTest.findAllArtistsAndAssociatedTracks();
        assertThat(returned).hasSize(2);
        assertThat(returned.get(0).getArtistTracks().get(1).getTrackName()).isEqualTo("Better Than Ever");
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
    void itShouldFindAndAddAllTracksByArtistID() {
        // Given
        Long id = 1L;
        Artist artist = new Artist(
                id,
                "Billy",
                "www.imageurl.com",
                PRO.ASCAP,
                "22321",
                null,
                null
        );
        Optional<Artist> artistOpt = Optional.of(artist);
        when(artistRepo.findById(id)).thenReturn(artistOpt);

        Track track = new Track(
                id,
                "Better Than Ever",
                "www.imageUrl.com",
                "www.trackUrl.com",
                321,
                Collections.singletonList(artist),
                null
        );
        Optional<Track> trackOpt = Optional.of(track);
        when(trackRepo.findById(id)).thenReturn(trackOpt);

        // When

        // Then
        assertEquals("Billy", artistRepo.findById(id).get().getArtistName());
        assertEquals("Better Than Ever", trackRepo.findById(id).get().getTrackName());

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

        Optional<Artist> artistOpt = Optional.of(billy);
        when(artistRepo.findById(id)).thenReturn(artistOpt);

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

        Optional<Artist> editArtistOpt = Optional.of(jackie);
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

        Optional<Artist> artistOpt = Optional.of(jackie);
        when(artistRepo.findById(2L)).thenReturn(artistOpt);

        // When
        underTest.deleteArtistById(1L);

        // Then
        verify(artistRepo, times(1)).deleteById(1L);

        assertEquals(Optional.empty(), artistRepo.findById(1L));
        assertEquals("Jackie", artistRepo.findById(2L).get().getArtistName());
    }

}

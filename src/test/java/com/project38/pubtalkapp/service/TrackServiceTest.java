package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.Track;
import com.project38.pubtalkapp.repo.TrackRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class TrackServiceTest {

    @Mock
    private TrackRepo trackRepo;
    @InjectMocks
    private TrackService underTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldFindAllTracks() {
        // Given
        List<Artist> artists = new ArrayList<>();
        List<Track> trackList = Arrays.asList(
                new Track(
                        1L,
                        "intro",
                        "www.imageurl.com",
                        "/path/to/intro.wav",
                        256,
                        artists
                ),
                new Track(
                        2L,
                        "next_up",
                        "www.imageurl.com",
                        "/path/to/next_up.wav",
                        305,
                        artists
                )
        );

        // When
        when(trackRepo.findAll()).thenReturn(trackList);

        // Then
        List<Track> returned = underTest.findAllTracks();
        assertThat(returned).hasSize(2);

    }

    @Test
    void itShouldFindTrackById() {
        // Given
        List<Artist> artists = new ArrayList<>();
        Track track = new Track(
                1L,
                "next_up",
                "www.imageurl.com",
                "/path/to/next_up.wav",
                305,
                artists
        );
        when(trackRepo.findById(1L)).thenReturn(Optional.of(track));

        // When
        Track returned = underTest.findTrackById(1L);

        // Then
        assertThat(returned).isEqualToComparingFieldByField(track);

    }

    @Test
    void itShouldCreateTrack() {
        // Given
        List<Artist> artists = new ArrayList<>();
        Track track = new Track(
                1L,
                "next_up",
                "www.imageurl.com",
                "/path/to/next_up.wav",
                305,
                artists
        );
        when(trackRepo.save(track)).thenReturn(track);

        // When
        Track returned = underTest.createTrack(track);

        // Then
        verify(trackRepo).save(track);
        assertThat(returned).isEqualToComparingFieldByField(track);

    }

    @Test
    void itShouldEditTrack() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldDeleteTrackById() {
        // Given
        List<Artist> artists = new ArrayList<>();
        Track track1 = new Track(
                1L,
                "intro",
                "www.imageurl.com",
                "/path/to/intro.wav",
                256,
                artists
        );
        Track track2 = new Track(
                2L,
                "next_up",
                "www.imageurl.com",
                "/path/to/next_up.wav",
                305,
                artists
        );
        trackRepo.saveAll(Arrays.asList(track1, track2));

        // When
        underTest.deleteTrackById(1L);

        when(trackRepo.findById(1L)).thenReturn(Optional.empty());
        when(trackRepo.findById(2L)).thenReturn(Optional.of(track2));

        // Then
        verify(trackRepo, times(1)).deleteById(1L);

        assertEquals(Optional.empty(), trackRepo.findById(1L));
        assertEquals("next_up", trackRepo.findById(2L).get().getTrackName());

    }
}

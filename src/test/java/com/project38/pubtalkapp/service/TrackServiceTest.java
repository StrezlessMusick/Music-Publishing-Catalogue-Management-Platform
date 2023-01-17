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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
        doReturn(trackList).when(trackRepo).findAll();

        // Then
        List<Track> returned = underTest.findAllTracks();
        assertThat(returned).hasSize(2);

    }

    @Test
    void itShouldFindTrackById() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldCreateTrack() {
        // Given

        // When

        // Then

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

        // When

        // Then

    }
}

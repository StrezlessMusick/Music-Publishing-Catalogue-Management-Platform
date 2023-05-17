package com.project38.appbackend.service;

import com.project38.appbackend.exception.TrackNotFoundException;
import com.project38.appbackend.model.Artist;
import com.project38.appbackend.model.Track;
import com.project38.appbackend.repo.TrackRepo;
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
                        artists,
                        null
                ),
                new Track(
                        2L,
                        "next_up",
                        "www.imageurl.com",
                        "/path/to/next_up.wav",
                        305,
                        artists,
                        null
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
        Long id = 1L;
        Track track = new Track(
                id,
                "next_up",
                "www.imageurl.com",
                "/path/to/next_up.wav",
                305,
                artists,
                null
        );
        Optional<Track> trackOpt = Optional.of(track);
        when(trackRepo.findById(id)).thenReturn(trackOpt);

        // When
        Track returned = underTest.findTrackById(id);

        // Then
        assertThat(returned).isEqualToComparingFieldByField(track);
    }

    @Test
    void itShouldThrowArtistNotFoundException() {
        // Given
        List<Artist> artists = new ArrayList<>();
        Long id = 1L;
        Track track = new Track(
                id,
                "next_up",
                "www.imageurl.com",
                "/path/to/next_up.wav",
                305,
                artists,
                null
        );
        Optional<Track> trackOpt = Optional.of(track);
        when(trackRepo.findById(id)).thenReturn(trackOpt);

        // When & Then
        assertEquals(trackOpt.get().getTrackName(), trackRepo.findById(id).get().getTrackName());
        assertThrows(TrackNotFoundException.class, () -> underTest.findTrackById(2L));

        Long tcId = 1L;
        try {
            trackRepo.findById(tcId);
        } catch (TrackNotFoundException e) {
            assertEquals(String.format("Track with id [%s] not found.", tcId),
                    e.getMessage());
        }
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
                artists,
                null
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
        List<Artist> artists = new ArrayList<>();
        Track track = new Track(
                1L,
                "intro",
                "www.imageurl.com",
                "/path/to/intro.wav",
                256,
                artists,
                null
        );
        trackRepo.save(track);

        Optional<Track> trackOpt = Optional.of(track);
        when(trackRepo.findById(1L)).thenReturn(trackOpt);

        // When
        Track trackEdit = new Track(
                1L,
                "first_on",
                "www.imageurl.com",
                "/path/to/first_on.wav",
                256,
                artists,
                null
        );
        underTest.editTrack(trackEdit);

        Optional<Track> trackEditOpt = Optional.of(trackEdit);
        when(trackRepo.findById(1L)).thenReturn(trackEditOpt);

        // Then
        verify(trackRepo).save(track);
        assertEquals(trackEdit.getTrackName(), trackRepo.findById(1L).get().getTrackName());

        assertNotEquals(Optional.empty(), trackRepo.findById(1L));
        assertNotEquals("intro", trackRepo.findById(1L).get().getTrackName());
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
                artists,
                null
        );
        Track track2 = new Track(
                2L,
                "next_up",
                "www.imageurl.com",
                "/path/to/next_up.wav",
                305,
                artists,
                null
        );
        trackRepo.saveAll(Arrays.asList(track1, track2));

        // When
        underTest.deleteTrackById(1L);

        when(trackRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<Track> track2Opt = Optional.of(track2);
        when(trackRepo.findById(2L)).thenReturn(track2Opt);

        // Then
        verify(trackRepo, times(1)).deleteById(1L);

        assertEquals(Optional.empty(), trackRepo.findById(1L));
        assertEquals(track2.getTrackName(), trackRepo.findById(2L).get().getTrackName());
    }

}

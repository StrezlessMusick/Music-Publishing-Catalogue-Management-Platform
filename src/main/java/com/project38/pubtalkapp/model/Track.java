package com.project38.pubtalkapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Track name is required!")
    private String trackName;
    @NotBlank(message = "Cover art url is required!")
    private String trackImageUrl;
    @NotBlank(message = "Length of track is required!")
    private Duration trackLength;


    @ManyToMany(mappedBy = "artistTracks")
    private List<Artist> artist = new ArrayList<>();

    @ManyToMany(mappedBy = "trackList")
    private List<Project> project = new ArrayList<>();
}

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
    //    @NotBlank(message = "Track name is required!")
    private String trackName;
    //    @NotBlank(message = "Cover art url is required!")
    private String trackImageUrl;
    private String trackUrl;
    //    @NotBlank(message = "Length of track is required!")
    private Integer trackLength;


    //    @OneToMany(mappedBy = "artistTracks")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "artist_track",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")}
    )
    private List<Artist> artist = new ArrayList<>();

    @ManyToMany(mappedBy = "trackList")
    private List<Project> project = new ArrayList<>();
}

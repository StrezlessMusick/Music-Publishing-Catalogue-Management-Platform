package com.project38.pubtalkapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artists")
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank(message = "Artist name is required!")
    private String artistName;

    //    @NotBlank(message = "Image url is required!")
    private String artistImageUrl;

    //    @NotBlank(message = "Artist PRO is required")
    @Enumerated(EnumType.STRING)
    private PRO pro;

    //    @NotBlank(message = "Artist PRO IPI number is required!")
    private String proIPI;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "artist_track",
            joinColumns = {@JoinColumn(name = "track_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")}
    )
    private List<Track> artistTracks = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "artist_project",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Project> artistProjects = new ArrayList<>();

}

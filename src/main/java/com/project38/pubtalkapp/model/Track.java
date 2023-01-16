package com.project38.pubtalkapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tracks")
public class Track implements Serializable {
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
//    private String soundExchange;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "track_artist",
            joinColumns = {@JoinColumn(name = "track_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")}
    )
//    @JsonManagedReference
    private List<Artist> artist = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "track_project",
            joinColumns = {@JoinColumn(name = "track_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
//    @JsonManagedReference
    private List<Project> project = new ArrayList<>();


}

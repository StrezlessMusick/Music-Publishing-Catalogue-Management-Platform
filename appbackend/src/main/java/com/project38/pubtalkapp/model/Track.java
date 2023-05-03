package com.project38.pubtalkapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tracks")
public class Track implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackName;
    private String trackImageUrl;
    private String trackUrl;
    private Integer trackLength;
//    private String soundExchange;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            targetEntity = Artist.class
    )
    @JoinTable(
            name = "artist_track",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")}
    )
    @JsonIgnore
    private List<Artist> artist = new ArrayList<>();

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;
}

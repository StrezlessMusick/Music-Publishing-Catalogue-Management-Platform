package com.project38.appbackend.model;

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
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String projectImageUrl;
    private Integer numOfTracks;
    private Integer projectLength;

    @ManyToMany(
            mappedBy = "artistProjects",
            targetEntity = Artist.class
    )
    private List<Artist> artist = new ArrayList<>();

    @OneToMany(
            mappedBy = "project",
            targetEntity = Track.class
    )
    private List<Track> trackList = new ArrayList<>();
}

package com.project38.pubtalkapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "projects")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotBlank(message = "Project name is required!")
    private String projectName;
//    @NotBlank(message = "Project cover art url is required!")
    private String projectImageUrl;
//    @NotBlank(message = "Number of tracks is required!")
    private Integer numOfTracks;
    private Integer projectLength;

    @ManyToMany(mappedBy = "artistProjects")
//    @JsonBackReference
    private List<Artist> artist = new ArrayList<>();

    @ManyToMany(mappedBy = "project")
//    @JsonBackReference
    private List<Track> trackList = new ArrayList<>();
}

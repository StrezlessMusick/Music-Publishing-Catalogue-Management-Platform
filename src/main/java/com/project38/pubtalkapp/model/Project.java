package com.project38.pubtalkapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @NotBlank(message = "Project name is required!")
    private String projectName;
    @NotBlank(message = "Project cover art url is required!")
    private String projectImageUrl;
    @NotBlank(message = "Number of tracks is required!")
    private Integer numOfTracks;
    private Duration projectLength;

    @OneToMany(targetEntity = Track.class)
    private ArrayList<Track> trackList;
}

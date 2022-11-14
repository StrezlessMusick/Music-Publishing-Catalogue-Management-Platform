package com.project38.pubtalkapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private ArrayList<Track> trackList;
    @NotBlank(message = "Number of tracks is required!")
    private Integer numOfTracks;
    private Duration projectLength;
}

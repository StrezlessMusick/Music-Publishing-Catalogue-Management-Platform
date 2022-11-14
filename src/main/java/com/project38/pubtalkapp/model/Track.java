package com.project38.pubtalkapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
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

    @ManyToOne
    private Artist artist;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;
}

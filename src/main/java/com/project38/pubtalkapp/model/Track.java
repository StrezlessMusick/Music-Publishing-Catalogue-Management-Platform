package com.project38.pubtalkapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
@Entity
@Table(name = "tracks")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackId;
    @NotBlank(message = "Track name is required!")
    private String trackName;
    @NotBlank(message = "Cover art url is required!")
    private String trackImageUrl;
    @NotBlank(message = "Length of track is required!")
    private Duration trackLength;

    @NotBlank(message = "Artist name is required!")
    @ManyToOne
    private Artist artistName;

    @ManyToOne
    private Project project;
}

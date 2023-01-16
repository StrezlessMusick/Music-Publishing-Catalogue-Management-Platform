package com.project38.pubtalkapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @ManyToMany(mappedBy = "artist")
    private List<Track> artistTracks = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "artist_project",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Project> artistProjects = new ArrayList<>();

}

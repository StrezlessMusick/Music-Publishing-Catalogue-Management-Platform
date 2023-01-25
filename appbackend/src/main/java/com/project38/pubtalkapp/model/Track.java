package com.project38.pubtalkapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    //    @NotBlank(message = "Track name is required!")
    private String trackName;
    //    @NotBlank(message = "Cover art url is required!")
    private String trackImageUrl;
    private String trackUrl;
    //    @NotBlank(message = "Length of track is required!")
    private Integer trackLength;
//    private String soundExchange;

    @ManyToMany(
            mappedBy = "artistTracks",
            targetEntity = Artist.class
    )
    @JsonIgnore
    private List<Artist> artist = new ArrayList<>();


//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private Project project;


}

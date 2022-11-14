package com.project38.pubtalkapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;
    @NotBlank(message = "Artist name is required!")
    private String artistName;
    @NotBlank(message = "Image url is required!")
    private String artistImageUrl;
    @NotBlank(message = "Artist PRO is required")
    @Enumerated(EnumType.STRING)
    private PRO pro;
    @NotBlank(message = "Artist PRO IPI number is required!")
    private String proIPI;




}

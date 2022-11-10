package com.project38.pubtalkapp.model;

import java.time.Duration;
import java.util.ArrayList;

public class Project {
    private Long id;
    private String projectName;
    private String projectImageUrl;
    private ArrayList<Track> trackList;
    private Integer numOfTracks;
    private Duration projectLength;
    private MetaData metaData;
}

package com.goutam.videoStream.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoId;

    private String title;

    private String description;

    private String contentType;

    private String filePath;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Course course;


}

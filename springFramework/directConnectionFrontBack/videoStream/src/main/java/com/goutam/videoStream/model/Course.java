package com.goutam.videoStream.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_id;

    private String title;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Video> videoList;
}

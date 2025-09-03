package com.example.studentEnrollment.models;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @Column(name = "Student_roll")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roll;

    @Column(name = "Student_name")
    private String name;

    private String city;

    @Column(name = "College_name")
    private String college;

    @Lob
    @Column(name = "Profile_picture", columnDefinition = "LONGBLOB")
    private byte[] image;

    public Student() {
        this.college = "SETGOI";
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

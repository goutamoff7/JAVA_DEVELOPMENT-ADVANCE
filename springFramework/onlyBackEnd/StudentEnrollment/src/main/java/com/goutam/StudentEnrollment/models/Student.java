package com.goutam.StudentEnrollment.models;

import jakarta.persistence.*;

@Entity
public class Student
{
    @Id
    @Column(name="Student_roll")
    private Integer roll;
    @Column(name="Student_name")
    private String name;
    private String city;
    @Column(name="College_name")
    private String collge;
    @Lob
    @Column(name="Profile_picture",columnDefinition = "LONGBLOB")
    private byte[] image;

    public Student()
    {
        this.collge="SETGOI";
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

    public String getCollge() {
        return collge;
    }

    public void setCollge(String collge) {
        this.collge = collge;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

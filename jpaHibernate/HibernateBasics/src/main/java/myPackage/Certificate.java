package myPackage;

import jakarta.persistence.Embeddable;

@Embeddable
public class Certificate
{

    private String courseName;
    private int duration;


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}


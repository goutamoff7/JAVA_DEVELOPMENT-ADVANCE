package io.goutam;

import javax.persistence.*;

@Entity
public class Student
{
    @Id
    @Column(name="Student_RollNo")
    private int roll;
    @Column(name="Student_Name",nullable = false)
    private String name;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Aadhar_Details")
    private AadharCard aadharCard; // Aadhar_Details (Foreign Key) field will be created in Student Table

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AadharCard getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(AadharCard aadharCard) {
        this.aadharCard = aadharCard;
    }

    @Override
    public String toString() {
        return "Student{" +
                "roll=" + roll +
                ", name='" + name + '\'' +
                ", aadharCard=" + aadharCard +
                '}';
    }
}

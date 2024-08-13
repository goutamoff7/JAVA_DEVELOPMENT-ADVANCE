package io.goutam;

import javax.persistence.*;

@Entity
public class AadharCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aadharNumber;
    private String name;
    private String address;


    @OneToOne(fetch=FetchType.LAZY , mappedBy = "aadharCard") // aadharCard is the Referenced Variable of Student class
    private Student student; // No Foreign key column will be created in AadharCard Table
    // but Data can be fetched in both way Student->AadharCard , AadharCard->Student (Bi-Directional Mapping)

    public int getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(int aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student getStudent() {
        return student;
    }

    //    @Override
//    public String toString() {
//        return "AadharCard{" +
//                "aadharNumber=" + aadharNumber +
//                ", address='" + address + '\''+
//                '}';
//    }
}

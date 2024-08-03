package myPackage;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name="student_address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private int id;

    @Column(length=50)
    private String street;

    @Column(length=25)
    private String city;

    private boolean Home;

    @Transient
    private int notRequired;  // This field will not include in the Table

    // Here we use java.time, so we don't need to add @Temporal annotation
    private LocalDate entryDate;

    private LocalTime entryTime;

    private LocalDateTime entryTimeStamp;

    @Lob
    private byte[] image;

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isHome() {
        return Home;
    }

    public void setHome(boolean home) {
        Home = home;
    }

    public int getNotRequired() {
        return notRequired;
    }

    public void setNotRequired(int notRequired) {
        this.notRequired = notRequired;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public void setEntryTimeStamp(LocalDateTime entryTimeStamp) {
        this.entryTimeStamp = entryTimeStamp;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}

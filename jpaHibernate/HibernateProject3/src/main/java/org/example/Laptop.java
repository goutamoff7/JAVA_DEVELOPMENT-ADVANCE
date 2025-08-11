package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Cacheable
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private int id;

    @Column(name = "laptop_brand")
    private String brand;

    @Column(name = "laptop_model")
    private String model;

    @Column(name = "laptop_ram")
    private int ram;

//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;

    @ManyToMany(mappedBy = "laptops")
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ram=" + ram +
                '}';
    }
}

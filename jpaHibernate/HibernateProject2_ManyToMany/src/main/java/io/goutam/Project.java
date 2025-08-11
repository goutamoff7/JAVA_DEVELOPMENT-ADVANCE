package io.goutam;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @Column(name = "Project_id")
    private int pid;

    @Column(name = "Project_name")
    private String pName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(
            name = "Project_Employee_Relation",
            joinColumns = @JoinColumn(name = "Project_id"),
            inverseJoinColumns = @JoinColumn(name = "Employee_id")
    )
    private List<Employee> employees = new ArrayList<>();

    // Getters and Setters
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

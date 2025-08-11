package io.goutam;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @Column(name = "Employee_id")
    private int eid;

    @Column(name = "Employee_name")
    private String eName;

    @ManyToMany(mappedBy = "employees",cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();

    // Getters and Setters
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}

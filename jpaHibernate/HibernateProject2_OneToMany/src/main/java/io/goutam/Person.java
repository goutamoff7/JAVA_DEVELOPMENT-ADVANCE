package io.goutam;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person
{
    @Id
    @Column(name="Person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="Person_name",nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "person")
    private List<Orders> orders;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}

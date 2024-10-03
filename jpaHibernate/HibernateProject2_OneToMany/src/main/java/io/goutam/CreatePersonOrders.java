package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


public class CreatePersonOrders
{
    public static void main(String[] args) {
    // Here Person is the Parent Table and Orders is the Child Table
    // as Orders has the Foreign Key which takes reference from Person

    Person p1 = new Person();
    p1.setName("Goutam Dam");

    Person p2 = new Person();
    p2.setName("Arpan Kundu");

    Orders o1 = new Orders();
    o1.setName("Laptop");
    o1.setPerson(p1);

    Orders o2 = new Orders();
    o2.setName("Mobile");
    o2.setPerson(p2);

    Orders o3 = new Orders();
    o3.setName("Computer");
    o3.setPerson(p1);



        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_create");
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(o1);
        entityManager.persist(o2);
        entityManager.persist(o3);
        entityTransaction.commit();

    }
}

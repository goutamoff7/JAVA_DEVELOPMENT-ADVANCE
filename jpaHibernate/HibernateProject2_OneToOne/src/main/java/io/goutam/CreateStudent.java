package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class CreateStudent
{
    public static void main(String[] args) {
// Here AadharCard is the Parent Table and Student is the Child Table
// as Student has the Foreign Key which takes reference from AadharCard

        AadharCard ac1 = new AadharCard();
        ac1.setName("Goutam Dam");
        ac1.setAddress("Bankura");

        AadharCard ac2 = new AadharCard();
        ac2.setName("Arpan Kundu");
        ac2.setAddress("Durgapur");

        AadharCard ac3 = new AadharCard();
        ac3.setName("Manojit Das");
        ac3.setAddress("Arambag");

        Student s1 = new Student();
        s1.setRoll(24);
        s1.setName("Goutam Dam");
        s1.setAadharCard(ac1);

        Student s2 = new Student();
        s2.setRoll(25);
        s2.setName("Arpan Kundu");
        s2.setAadharCard(ac2);

        Student s3 = new Student();
        s3.setRoll(29);
        s3.setName("Manojit Das");
        s3.setAadharCard(ac3);




        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_create");
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(ac1);
        entityManager.persist(ac2);
        entityManager.persist(ac3);
        entityManager.persist(s1);
        entityManager.persist(s2);
        entityManager.persist(s3);
        entityTransaction.commit();

    }
}

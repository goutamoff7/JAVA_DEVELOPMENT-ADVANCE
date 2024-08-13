package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DeleteStudent
{
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_validate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Student s = entityManager.find(Student.class,29);
        if(s!=null)
        {
            AadharCard ac = s.getAadharCard();

            entityTransaction.begin();
            entityManager.remove(s); // Record deleted from Student Table
            entityManager.remove(ac); // Record deleted from AadharCard Table

            entityTransaction.commit();
            System.out.println("Record Successfully Deleted");
        }
        else
            System.out.println("Record Not Found");

    }
}

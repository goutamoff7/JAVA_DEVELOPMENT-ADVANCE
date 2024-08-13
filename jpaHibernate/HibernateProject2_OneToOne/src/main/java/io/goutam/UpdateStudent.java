package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UpdateStudent
{
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_validate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        System.out.println("Direct Updation on Parent Table (AadharCard)");
        AadharCard ac = entityManager.find(AadharCard.class,1);
        if(ac!=null)
        {
            System.out.println("Old Address = "+ac.getAddress());
            ac.setAddress("Barjora");
            entityTransaction.begin();
            entityManager.persist(ac);
            entityTransaction.commit();
            System.out.println("Updation Successful \n New Address = "+ac.getAddress());
        }
        else
            System.out.println("Record Not Found");

        System.out.println("Indirect Updation on Parent Table via Child Table (Student)");
        Student s1 = entityManager.find(Student.class,24);
        if(s1!=null)
        {
            AadharCard ac2 = s1.getAadharCard();
            System.out.println("Old Address = "+ ac2.getAddress());
            ac2.setAddress("Durgapur");
            entityTransaction.begin();
            entityManager.persist(ac2);
            entityTransaction.commit();
            System.out.println("Updation Successful \n New Address = "+ac2.getAddress());
        }
        else
            System.out.println("Record Not Found");

}
}

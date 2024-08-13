package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ReadStudent
{
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_validate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Data fetched Student Table to AadharCard Table
        Student s1 = entityManager.find(Student.class,24);
        if(s1!=null)
        {
            AadharCard ac = s1.getAadharCard();
            System.out.println("RollNo : "+ s1.getRoll()+
                    "\nName : "+ s1.getName()+
                    "\nAadharNumber : "+ac.getAadharNumber()+
                    "\nAddress : "+ac.getAddress());
        }
        else
            System.out.println("Record Not Found");

        System.out.println();

        // Data fetched AadharCard Table to Student Table
        AadharCard card = entityManager.find(AadharCard.class,1);
        if(card!=null)
        {
            Student student = card.getStudent();
            System.out.println("RollNo : "+ student.getRoll()+
                    "\nName : "+ student.getName()+
                    "\nAadharNumber : "+card.getAadharNumber()+
                    "\nAddress : "+card.getAddress());
        }
        else
            System.out.println("Record Not Found");

    }
}

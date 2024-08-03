package myPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmbedDemo
{
    public static void main(String[] args)
    {

        Certificate c1 = new Certificate();
        c1.setCourseName("Jpa-hibernate");
        c1.setDuration(2);

        Student s1 = new Student();
        s1.setName("Goutam Dam");
        s1.setCrtf(c1);
        // all the fields of Certificate Table(@Embeddable) will be added in the student table (@Embedded)


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_create");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(s1);
        entityTransaction.commit();

    }



}

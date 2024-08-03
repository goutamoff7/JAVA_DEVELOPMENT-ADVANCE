package myPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.*;

public class CreateData

{
    public static void main(String[] args) throws IOException
    {
        Student s1 = new Student();
        s1.setName("Goutam Dam");

        Address a1 = new Address();
        a1.setStreet("Ramkrishna Pally");
        a1.setCity("Durgapur");
        a1.setHome(true);
        a1.setNotRequired(1);
        a1.setEntryDate(LocalDate.now());
        a1.setEntryTime(LocalTime.now());
        a1.setEntryTimeStamp(LocalDateTime.now());

        // Reading the Image from the file and fills the array with the bytes
        FileInputStream fis = new FileInputStream("src\\main\\java\\myPackage\\logo.png");
        byte image[] = new byte[fis.available()];
        fis.read(image);

        a1.setImage(image);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_create");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(s1);
        entityManager.persist(a1);
        entityTransaction.commit();
    }



}

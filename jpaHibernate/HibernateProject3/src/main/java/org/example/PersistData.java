package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PersistData {
    public static void main(String[] args) {

        Laptop laptop1 = new Laptop();
        laptop1.setBrand("Dell");
        laptop1.setModel("inspiron");
        laptop1.setRam(8);

        Laptop laptop2 = new Laptop();
        laptop2.setBrand("Asus");
        laptop2.setModel("rog");
        laptop2.setRam(8);

        Laptop laptop3 = new Laptop();
        laptop3.setBrand("Lenovo");
        laptop3.setModel("ThinkPad");
        laptop3.setRam(16);

        Student student1 = new Student();
        student1.setId(24);
        student1.setName("Goutam Dam");
        student1.setMarks(85);
        student1.setLaptops(List.of(laptop1, laptop2));

        Student student2 = new Student();
        student2.setId(25);
        student2.setName("Arpan Kundu");
        student2.setMarks(85);
        student2.setLaptops(List.of(laptop2, laptop3));

//        laptop1.setStudents(List.of(student1)); // No need to set values on both side
//        laptop2.setStudents(List.of(student1,student2)); // set values to the side which
//        laptop3.setStudents(List.of(student2)); // only going to persist


//        Configuration configuration = new Configuration();
//        configuration.configure(); // configuring as per hibernate.cfg.xml file
//        configuration.addAnnotatedClass(org.example.Student.class);
//        SessionFactory sessionFactory = configuration.buildSessionFactory();

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClasses(Student.class, Laptop.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

//        session.persist(laptop1); // cascade used on laptops field of Student table
//        session.persist(laptop2); // so no need to persist laptop separately
//        session.persist(laptop3); // also mapping done by laptops field
        session.persist(student1);
        session.persist(student2);

        transaction.commit();

        session.close();

        System.out.println(student1);

    }
}

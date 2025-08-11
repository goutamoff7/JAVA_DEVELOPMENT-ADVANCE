package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdateData {
    public static void main(String[] args) {

        Session session = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class)
                .buildSessionFactory()
                .openSession();

        Transaction transaction = session.beginTransaction();

        Laptop laptop = session.find(Laptop.class,3);

        Student student = new Student();
        student.setId(17);
        student.setName("Bapi Mandal");
        student.setMarks(80);
        student.setLaptops(List.of(laptop));

        session.merge(student); // if not found create new entity

        transaction.commit();

        session.close();

        System.out.println(student);
    }
}

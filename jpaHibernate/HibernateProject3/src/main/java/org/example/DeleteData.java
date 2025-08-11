package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DeleteData {
    public static void main(String[] args) {

        Session session = new Configuration()
                .configure()
                .addAnnotatedClasses(Student.class, Laptop.class)
                .buildSessionFactory()
                .openSession();

        Student student = session.find(Student.class,17);

        Transaction transaction = session.beginTransaction();

        session.remove(student);

        transaction.commit();

        session.close();
    }
}

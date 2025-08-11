package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class FetchData {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClasses(Student.class, Laptop.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();


        Student student = session.find(Student.class,25);

        System.out.println(student);

        Student student1 = session.getReference(Student.class,25);

        System.out.println(student1);

        // SQL Query - select * from student where student_marks = 85; (student_marks) is the field of DB
        // HQL Query - from Student where marks = 85; (marks) is the field of Student class

//        String hql = "from Student where marks = 85";
//        String hql = "from Student where name = 'Goutam Dam'";
        String hql = "from Student s " +
                     "join s.laptops l where l.brand = 'Dell'";  // laptops field in Student must be EAGER loaded
        Query<Student> query = session.createQuery(hql, Student.class);
        List<Student> resultList = query.getResultList();
        System.out.println(resultList);


        // Fetch data with user given value using ':'

        int marks = 85;

        String hql2 = "select s from Student s where s.marks = :marks";
        Query<Student> query2 = session.createQuery(hql2, Student.class);
        query2.setParameter("marks",marks);
        List<Student> resultList2 = query2.getResultList();
        System.out.println(resultList2);


        // Fetch data with user given value using '?1, ?2, ?...'
        int id = 24;

        String hql3 = "select s from Student s where s.id = ?1";
        Query<Student> query3 = session.createQuery(hql3, Student.class);
        query3.setParameter(1,id);
        List<Student> resultList3 = query3.getResultList();
        System.out.println(resultList3);

        // Fetch specific field - name

        String hql4 = "select name from Student where marks = 85";
        Query<String> query4 = session.createQuery(hql4, String.class);
        List<String> resultList4 = query4.getResultList();
        System.out.println(resultList4);

        // Fetch more than 1 field - name,marks

        String hql5 = "select name, marks from Student where marks = 85";
        Query<Object[]> query5 = session.createQuery(hql5, Object[].class);
        List<Object[]> resultList5 = query5.getResultList();
        for (Object[] row : resultList5) {
            String studentName = (String) row[0];
            Integer studentMarks = (Integer) row[1];
            System.out.println(studentName + " - " + studentMarks);
        }



    }
}

package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class L2CacheDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClasses(Student.class, Laptop.class)
                .buildSessionFactory();

        // similar query on single session
        System.out.println("Default : L1 cache");
        Session session1 = sessionFactory.openSession();

        Laptop laptop1 = session1.find(Laptop.class,1);
        System.out.println(laptop1);

// Here as we execute same query on same session Hibernate uses L1 cache
// and will not fire the same query again
        Laptop laptop2 = session1.find(Laptop.class,1);
        System.out.println(laptop2);

        session1.close();

        System.out.println();

        // similar query on different session
        System.out.println("Third Party : L2 cache");
        Session session2 = sessionFactory.openSession();

        Laptop laptop3 = session2.find(Laptop.class,2);
        System.out.println(laptop3);

        session2.close();

// Here as we execute same query but on different session Hibernate will not use
// L1 cache and fire the same query again but after configuring L2 cache
// Hibernate will not fire the same query again

        Session session3 = sessionFactory.openSession();
        Laptop laptop4 = session3.find(Laptop.class,2);
        System.out.println(laptop4);

        session3.close();

        // Demonstration of L2 cache using ehCache and hibernate-jcache (use maven dependency)
        // and also need to add @Cacheable annotation on the Entity classes
        // so that Hibernate will use L2 cache and not fire the same query again

        // but ehcache depends on javax.cache / JSR-107 API.
        // This brings in JAXB (org.glassfish.jaxb:jaxb-runtime),
        // which in turn depends on com.sun.istack:istack-commons-runtime.
        // Older JAXB metadata still points to old HTTP maven.java.net repos
        // and Since Maven 3.8.1 http repositories are blocked
        // solution - add this dependency inside
        // <dependencyManagement>
        //           <dependencies>
//                   </dependencies>
//      </dependencyManagement>

//        <dependencyManagement>
//            <dependencies>
//                <dependency>
//                    <groupId>org.glassfish.jaxb</groupId>
//                    <artifactId>jaxb-runtime</artifactId>
//                    <version>4.0.5</version>
//                </dependency>
//            </dependencies>
//        </dependencyManagement>

//        After configuring also if not L2 cache works then configure hibernate.cfg.xml
//        to provide the L2 cache provider details

    }
}

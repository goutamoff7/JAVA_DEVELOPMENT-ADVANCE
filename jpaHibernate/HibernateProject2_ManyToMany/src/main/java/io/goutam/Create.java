package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Create {
    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_create");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ent = em.getTransaction();

        // Create Employee objects
        Employee emp1 = new Employee();
        emp1.setEid(1);
        emp1.seteName("John Doe");

        Employee emp2 = new Employee();
        emp2.setEid(2);
        emp2.seteName("Jane Smith");

        // Create Project objects
        Project proj1 = new Project();
        proj1.setPid(101);
        proj1.setpName("Project Alpha");

        Project proj2 = new Project();
        proj2.setPid(102);
        proj2.setpName("Project Beta");

        // Set relationships
//        List<Project> emp1Projects = new ArrayList<>();
//        emp1Projects.add(proj1);
//        emp1Projects.add(proj2);
//        emp1.setProjects(emp1Projects);
//
//        List<Project> emp2Projects = new ArrayList<>();
//        emp2Projects.add(proj1);
//        emp2.setProjects(emp2Projects);

        List<Employee> proj1Employees = new ArrayList<>();
        proj1Employees.add(emp1);
        proj1Employees.add(emp2);
        proj1.setEmployees(proj1Employees);

        List<Employee> proj2Employees = new ArrayList<>();
        proj2Employees.add(emp1);
        proj2.setEmployees(proj2Employees);

        // Persist the entities
        ent.begin();
        em.persist(emp1);
        em.persist(emp2);
        em.persist(proj1);
        em.persist(proj2);
        ent.commit();

        System.out.println("Data saved successfully!");
    }
}

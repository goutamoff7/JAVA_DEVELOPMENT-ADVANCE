package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Read {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_validate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//      Fetch data from Employee to Project
        Employee e1 = entityManager.find(Employee.class, 1);
        if (e1 != null) {
            System.out.println("Employee " + e1.getEid() + " works in Projects");
            System.out.println("Employee Id : " + e1.getEid() +
                    "\nEmployee Name : " + e1.geteName());
            for (Project p : e1.getProjects()) {
                System.out.println("Project Id : " + p.getPid() +
                        "\n Project Name : " + p.getpName());
            }
        } else
            System.out.println("Employee Id not Found");

        System.out.println();

        //  Fetch data from Project to Employee
        Project p1 = entityManager.find(Project.class, 101);
        if (p1 != null) {
            System.out.println("Projects " + p1.getPid()+ " has Employee");
            System.out.println("Project Id : " + p1.getPid() +
                    "\nProject Name : " + p1.getpName());
            for (Employee e : p1.getEmployees()) {
                System.out.println("Employee Id : " + e.getEid() +
                        "\n Employee Name : " + e.geteName());
            }
        } else
            System.out.println("Project Id not Found");
    }
}

package io.goutam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReadPersonOrders
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_validate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Fetch Person from Orders
        Orders order = entityManager.find(Orders.class,1);
        if(order!=null)
        {
            Person p = order.getPerson();
            System.out.println("Order Id : "+order.getId()+
                            "\nItem Name : "+order.getName()+
                    "\nCustomer Id : "+p.getId()+
                    "\nPerson Name : "+p.getName());
        }
        else
            System.out.println("Order Id not Found");

        System.out.println();

        //Fetch OrderList from Person
        Person person = entityManager.find(Person.class,1);
        if(person!=null)
        {
            for(Orders items:person.getOrders())
            {
                System.out.println("Order Id : "+items.getId()+
                        "\nItem Name : "+items.getName()+
                        "\nCustomer Id : "+person.getId()+
                                "\nPerson Name : "+person.getName());
                System.out.println();
            }

        }
        else
            System.out.println("Order Id not Found");
    }


}

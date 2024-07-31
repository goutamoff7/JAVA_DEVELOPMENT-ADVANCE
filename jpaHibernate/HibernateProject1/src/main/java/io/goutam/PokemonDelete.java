package io.goutam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PokemonDelete {

	public static void main(String[] args) {
		//1. fetch the Details - entityManager.find(entityClass, primaryKey)
		//2. transaction (begin -> remove - > commit)
		
		//EntityManagerFactory -> EntityManager -> EntityTransaction
		//createEntityManagerFactory() -> createEntityManager() -> getTransaction() 
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_sql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		//We want to delete "Squirtle"
		Pokemon p1 = entityManager.find(Pokemon.class, "Squirtle");
		if(p1!=null)
		{
			entityTransaction.begin();
			entityManager.remove(p1);	
			entityTransaction.commit();
			System.out.println("Record Deleted Successfully");
		}
		else
			System.out.println("Record Not Exists");
		
		//We want to delete "Pikachu"
		Pokemon p2 = entityManager.find(Pokemon.class, "pika"); // can't find
		if(p2!=null)
		{
			entityTransaction.begin();
			entityManager.remove(p2);	
			entityTransaction.commit();
			System.out.println("Record Deleted Successfully");
		}
		else
			System.out.println("Record Not Exists");

	}

}

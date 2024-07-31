package io.goutam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PokemonUpdate {

	public static void main(String[] args) {
		//1. fetch the Details - entityManager.find(entityClass, primaryKey)
		//2. Update the Details - calling setters() method
		//3. transaction (begin -> persist - > commit) 
		
		//EntityManagerFactory -> EntityManager -> EntityTransaction
		//createEntityManagerFactory() -> createEntityManager() -> getTransaction() 
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_sql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		//We want to update the power of "Raichu" to 90
		Pokemon p1 = entityManager.find(Pokemon.class, "raichu");
		if(p1!=null)
		{
			p1.setPower(90);
			entityTransaction.begin();
			entityManager.persist(p1);	
			entityTransaction.commit();
			System.out.println("Record Updated Successfully");
		}
		else
			System.out.println("Record Not Exists");
		
		//We want to update the power of "Pikachu" to 90
		Pokemon p2 = entityManager.find(Pokemon.class, "pika"); // can't find
		if(p2!=null)
		{
			p2.setPower(90);
			entityTransaction.begin();
			entityManager.persist(p2);	
			entityTransaction.commit();
			System.out.println("Record Updated Successfully");
		}
		else
			System.out.println("Record Not Exists");

	}

}

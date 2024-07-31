package io.goutam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PokemonRead {

	public static void main(String[] args) {
		//1. fetch the Details - entityManager.find(entityClass, primaryKey);
		//2. Print the Details
		
		//EntityManagerFactory -> EntityManager
		//createEntityManagerFactory() -> createEntityManager() 
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_sql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Pokemon p1 = entityManager.find(Pokemon.class, "raichu");
		System.out.println(p1);
		Pokemon p2 = entityManager.find(Pokemon.class, "pika"); // can't find "pika" as primary key 
		System.out.println(p2);

	}

}

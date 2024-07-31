package io.goutam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PokemonCreateInsert {

	public static void main(String[] args) {
		Pokemon p1 = new Pokemon();
		p1.setName("Pikachu");
		p1.setType("Electric");
		p1.setPower(100);
		p1.setCol(Colour.YELLOW);
		
		Pokemon p2 = new Pokemon();
		p2.setName("Squirtle");
		p2.setType("Water");
		p2.setPower(70);
		p2.setCol(Colour.BLUE);
		
		Pokemon p3 = new Pokemon();
		p3.setName("Raichu");
		p3.setType("Electric");
		p3.setPower(80);
		p3.setCol(Colour.CREAM);
		
		//EntityManagerFactory -> EntityManager -> EntityTransaction
		//createEntityManagerFactory() -> createEntityManager() -> getTransaction() 
		//persistence-unit name="jpa_sql"
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_sql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		// transaction (begin -> persist - > commit) 
		
		entityTransaction.begin();
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.persist(p3);
		entityTransaction.commit();		

	}

}

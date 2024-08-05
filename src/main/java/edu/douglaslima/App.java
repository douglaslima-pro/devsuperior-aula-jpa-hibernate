package edu.douglaslima;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.douglaslima.domain.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
	
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-aulajpa");
		EntityManager em = emf.createEntityManager();
		
		/*
		Pessoa p1 = new Pessoa(null, "Douglas", "douglaslima-pro@outlook.com");
		Pessoa p2 = new Pessoa(null, "Maria", "maria@gmail.com");
		Pessoa p3 = new Pessoa(null, "Eduardo", "eduardo@gmail.com");
		
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		
		Pessoa p = em.find(Pessoa.class, 3);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		System.out.println(p);
		*/
		
		Map<String, Object> properties = em.getProperties();
		Set<Entry<String, Object>> propertiesEntries = properties.entrySet();
		propertiesEntries.forEach(entry -> System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue())));
		
		em.close();
		emf.close();
	}
	
}

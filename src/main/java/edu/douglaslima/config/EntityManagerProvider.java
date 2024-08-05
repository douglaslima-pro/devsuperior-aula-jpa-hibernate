package edu.douglaslima.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {
	
	private static final String PERSISTENCE_UNIT = "mysql-aulajpa";

	private static EntityManagerProvider singleton;
	private static EntityManagerFactory emf;
	
	private EntityManagerProvider() {}
	
	public static EntityManagerProvider getInstance() {
		if (singleton == null) {
			singleton = new EntityManagerProvider();
		}
		return singleton;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		}
		return emf;
	}
	
	public static void closeEntityManagerFactory() {
		if (emf.isOpen() || emf != null) {
			emf.close();
		}
		emf = null;
	}

}

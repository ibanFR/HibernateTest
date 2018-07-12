package com.ibanfr.hibernate.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HibernateUtil5 {
	
	private static Logger logger = LoggerFactory.getLogger(HibernateUtil5.class);
	
//	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//			.configure() // configures settings from hibernate.cfg.xml
//			.build();
	
	private static  SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			return sessionFactory;
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
			logger.error("SessionFactory creation failed.",e);
			throw new ExceptionInInitializerError(e);
		}
		
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	
}

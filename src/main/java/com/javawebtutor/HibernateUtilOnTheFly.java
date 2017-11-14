package com.javawebtutor;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

public class HibernateUtilOnTheFly {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			
			// Create the SessionFactory from hibernate.cfg.xml
			StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
//			HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
			strongEncryptor.setAlgorithm("PBEWITHHMACSHA1ANDAES_128");
	        strongEncryptor.setPassword("jasypt");
//			strongEncryptor.setAlgorithm("PBEWITHMD5ANDDES");
//	        strongEncryptor.setPassword("test");

	        //	        registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);
	        
	        
	        Configuration config = new Configuration().configure();
	        String pass = config.getProperty("hibernate.connection.password");
	        config.setProperty("hibernate.connection.password", strongEncryptor.decrypt(pass));
	        
	        
			return config.buildSessionFactory();
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
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

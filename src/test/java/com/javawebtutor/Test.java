package com.javawebtutor;

import java.util.Date;

import org.hibernate.Session;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class Test {
	public static void main(String[] args) {

		Test t = new Test();
//		t.strongEncryptor();
		 t.encryptPassword();
//		 t.registerEncryptor();
		 t.decryptPassword();
//		t.createUser();
//		t.createUserOnTheFly(2);

	}

	public void decryptPassword() {

//		ConfigurablePasswordEncryptor strongEncryptor = new ConfigurablePasswordEncryptor();
//		strongEncryptor.setAlgorithm("SHA-256");
		
//		StrongPasswordEncryptor strongEncryptor = new StrongPasswordEncryptor();
//		System.out.println(strongEncryptor.checkPassword("mysqlpassword", "o65tHf0efDQoOJql28OSNa8R9XHB2JOUtBEIlJiKv6z6XS01ksvcwA=="));
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();

//		strongEncryptor.setPassword("test"); // we HAVE TO set a password
//		strongEncryptor.setAlgorithm("PBEWITHMD5ANDDES"); // optionally set the
//															// algorithm

//		strongEncryptor.setPassword("jasypt"); // we HAVE TO set a password
//		strongEncryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // optionally set the
//															// algorithm
		

//		strongEncryptor.setPassword("jasypt"); // we HAVE TO set a password
//		strongEncryptor.setAlgorithm("PBEWITHHMACSHA1ANDAES_128"); // optionally set the
//															 algorithm
		
		strongEncryptor.setPassword("jasypt"); // we HAVE TO set a password
		strongEncryptor.setAlgorithm("PBEWITHSHA1ANDDESEDE");
		

		
//		String decryptedText = strongEncryptor.decrypt("fgF4jcQBHVEn0AHqD8rB6zKZCDRReEYn"); //PBEWithMD5AndTripleDES
//		String decryptedText = strongEncryptor.decrypt("kCUSThP5mL1pI+LHmJ5qUo9IwhdyM+AXs5reDSxkUus="); //PBEWITHHMACSHA1ANDAES_128
		String decryptedText = strongEncryptor.decrypt("HoGtLaZlIiiNPMRVtgGiOl0TSTG6Xzgw"); //PBEWITHSHA1ANDDESEDE

		System.out.println(decryptedText);

	}

	public void registerEncryptor() {
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
//		strongEncryptor.setAlgorithm("PBEWITHMD5ANDDES");
//		strongEncryptor.setPassword("test");

		strongEncryptor.setAlgorithm("PBEWITHHMACSHA1ANDAES_128");
		strongEncryptor.setPassword("jasypt");

		
		registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);

		// Properties props = new EncryptableProperties(strongEncryptor);
		// try {
		// props.load(new FileInputStream("hibernate.cfg.xml"));
		//
		// String datasourcePassword = props.getProperty("datasource.password");
		//
		// System.out.println(datasourcePassword);
		//
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public void strongEncryptor(){
		
//		StrongPasswordEncryptor strongEncryptor = new StrongPasswordEncryptor();
//		System.out.println(strongEncryptor.encryptPassword("mysqlpassword"));
		
		ConfigurablePasswordEncryptor strongEncryptor = new ConfigurablePasswordEncryptor();
		strongEncryptor.setAlgorithm("SHA-256");
		System.out.println(strongEncryptor.encryptPassword("mysqlpassword"));
	}
	
	public void encryptPassword() {

		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();

		strongEncryptor.setPassword("jasypt"); // we HAVE TO set a password
//		strongEncryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // optionally
//																// set the
																// algorithm
//		strongEncryptor.setAlgorithm("PBEWITHHMACSHA1ANDAES_128");
		
		
		strongEncryptor.setAlgorithm("PBEWITHSHA1ANDDESEDE");
		
		
		
		String encryptedText = strongEncryptor.encrypt("mysqlpassword");

		System.out.println(encryptedText);
	}

	public void createUser() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		User user = new User();

		// user.setUserId(1);
		// user.setUsername("Mukesh");
		// user.setCreatedBy("Google");
		// user.setCreatedDate(new Date());

		user.setUserId(3);
		user.setUsername("Ivan2");
		user.setCreatedBy("etu");
		user.setCreatedDate(new Date());

		session.save(user);
		session.getTransaction().commit();
	}

	public void createUserOnTheFly(int numUser) {

		Session session = HibernateUtilOnTheFly.getSessionFactory().openSession();
		
		session.beginTransaction();

		for (int i = 0; i < numUser; i++) {
			
			User user = new User();

			user.setUsername("Ivan2");
			user.setCreatedBy("etu");
			user.setCreatedDate(new Date());

			session.save(user);
		}
		
		session.getTransaction().commit();
		
//		session.close();
	}
	
	
}

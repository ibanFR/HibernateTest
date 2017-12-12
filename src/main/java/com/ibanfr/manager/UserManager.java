/**
 * 
 */
package com.ibanfr.manager;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.ibanfr.hibernate.connection.HibernateUtil;
import com.ibanfr.hibernate.model.User;

/**
 * @author IVAN
 *
 */
public class UserManager {
	
	public User createUser(String name, String createdBy){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = new User();

		user.setUsername(name);
		user.setCreatedBy(createdBy);
		user.setCreatedDate(new Date());

		session.save(user);
		
		session.getTransaction().commit();
		
		return user;
	}

	public void createUsers(int numUser) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
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
	
	public List<User> listAllUsers(){
		
		return null;
	}
}

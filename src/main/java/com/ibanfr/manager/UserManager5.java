/**
 * 
 */
package com.ibanfr.manager;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.ibanfr.hibernate.connection.HibernateUtil;
import com.ibanfr.hibernate.connection.HibernateUtil5;
import com.ibanfr.hibernate.model.User;

/**
 * @author IVAN
 *
 */
public class UserManager5 {
	
	public User createUser(String name, String createdBy){
		
		Session session = HibernateUtil5.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = new User();

		user.setUsername(name);
		user.setCreatedBy(createdBy);
		user.setCreatedDate(new Date());

		session.save(user);
		
		session.getTransaction().commit();
		session.close();
		
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
		session.close();
		
//		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> listAllUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<User> result = (List<User>)session.createQuery( "from User" ).list();

		session.getTransaction().commit();
		session.close();
		return result;
	}
}

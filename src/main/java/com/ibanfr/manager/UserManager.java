/**
 * 
 */
package com.ibanfr.manager;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.ibanfr.hibernate.connection.HibernateUtil;
import com.ibanfr.hibernate.model.User;
import com.ibanfr.hibernate.model.User_;

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
		session.close();
		
		return user;
	}

	public void createUsers(int numUser) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();

		for (int i = 0; i < numUser; i++) {
			
			User user = new User();

			user.setUsername("testUser"+i);
			user.setCreatedBy("etu");
			user.setCreatedDate(new Date());

			session.save(user);
		}
		
		session.getTransaction().commit();
		session.close();
		
//		session.close();
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public List<User> listAllUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<User> result = session.createQuery( "from User", User.class ).list();

		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	/**
	 * Find user by id using the Criteria API as a type safe alternative to JPQL.
	 * <p>
	 *
	 *
	 * 
	 * @param userId User identifier
	 * @return User object
	 *
	 * @see <a hef="http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#criteria "> http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#criteria </a>
	 */
	public User findUserbyId(Integer userId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> userRoot = query.from(User.class);
		query.select(userRoot);
		query.where(builder.equal(userRoot.get(User_.USER_ID), userId));
		User u = session.createQuery(query).getSingleResult();
		
		session.getTransaction().commit();
		session.close();
		return u;
		
	}
}

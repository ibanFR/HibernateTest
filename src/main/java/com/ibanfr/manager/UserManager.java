/**
 * 
 */
package com.ibanfr.manager;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ibanfr.hibernate.connection.HibernateUtil;
import com.ibanfr.hibernate.model.User;
import com.ibanfr.hibernate.model.User_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author IVAN
 *
 */
public class UserManager {

	/**
	 * SLF4J Logger.
	 */
	private static Logger logger;

	static {
		logger = LoggerFactory.getLogger(HibernateUtil.class);
	}


	/**
	 * Save or update given {@link User} instance.
	 *
	 * @param user The <code>User</code> instance to save or update.
	 * @return Saved or udpated <code>User</code> instance.
	 */
	public User saveOrUpdateUser(User user){

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//TODO: Exception handling.
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			logger.error("Error saving or updating User={}",user, e);
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			throw e;
		}

		return user;
	}

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

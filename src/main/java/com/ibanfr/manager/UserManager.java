package com.ibanfr.manager;

import com.ibanfr.hibernate.dto.CountDetailsDTO;
import com.ibanfr.hibernate.dto.UserDetailsDTO;
import com.ibanfr.hibernate.connection.HibernateUtil;
import com.ibanfr.hibernate.model.Address;
import com.ibanfr.hibernate.model.User;
import com.ibanfr.hibernate.model.User_;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Manager class with database operations for {@link User} management.<p>
 *
 * @author IVAN
 */
public class UserManager {

	/**
	 * SLF4J Logger.
	 */
	private static final Logger logger;

	static {
		logger = LoggerFactory.getLogger(HibernateUtil.class);
	}

	public List<UserDetailsDTO> listUsersWithHQL(boolean useTupleTransformer) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		Query hqlQuery = session.createQuery(
				"select u.username as name,a.line1 as addressLine1, a.id as id" +
						" from User u " +
						"join Address a on a.id = u.address.id");


		if(useTupleTransformer) {
			hqlQuery.setTupleTransformer(Transformers.aliasToBean(UserDetailsDTO.class)); // This works
		}
		else {
			hqlQuery.setResultListTransformer(Transformers.aliasToBean(UserDetailsDTO.class));  // This fails
		}

		List<UserDetailsDTO> userDetails = hqlQuery.list();

		session.getTransaction().commit();

		return userDetails;
	}

	/**
	 * Test HQL where a value is aggregated
	 * @return
	 */
	public List<CountDetailsDTO> listAggregatedUserWithHQL() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List<CountDetailsDTO> countDetails = session.createQuery(
				"select count(*) as count, createdBy as createdBy from User group by createdBy")
				.setTupleTransformer(Transformers.aliasToBean(CountDetailsDTO.class))
				.list();

		session.getTransaction().commit();

		return countDetails;
	}


	public List<UserDetailsDTO> listUsersWithNativeQuery() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		Query nativeQuery = session.createNativeQuery(
				"select u.username as name,a.line1 as addressLine1, a.id as id " +
				"from user u " +
				"join address a on a.id = u.address_id")
				;


		nativeQuery.setTupleTransformer(Transformers.aliasToBean(UserDetailsDTO.class));  // does not work

		List<UserDetailsDTO> userDetails = nativeQuery.getResultList();

		session.getTransaction().commit();

		return userDetails;

	}

	/**
	 * Save or update given {@link User} instance.
	 *
	 * @param user The <code>User</code> instance to save or update.
	 * @return Saved or updated <code>User</code> instance.
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

	/**
	 * Create new {@link User} and persist it on db.
	 *
	 * @param name Name of the user.
	 * @param createdBy User performing operation.
	 * @return The persisted <code>User</code> instance.
	 *
	 * @deprecated Use {@link #saveOrUpdateUser(User)} instead.
	 */
	@Deprecated
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

			Address address = new Address();
			address.setLine1((i+1)+" Main St");
			user.setAddress(address);
			session.save(address);

			session.save(user);
		}
		
		session.getTransaction().commit();
		session.close();
		
//		session.close();
	}
	
	/**
	 * Find all users from db.
	 * 
	 * @return <code>User</code> list
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
	 *	<a hef="http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#criteria "> http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#criteria </a>
	 *
	 * @param userId User identifier
	 * @return User object
	 *
	 */
	public User findUserById(Integer userId) {
		
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

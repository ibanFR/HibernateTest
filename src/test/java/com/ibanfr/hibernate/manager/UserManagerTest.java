package com.ibanfr.hibernate.manager;

import com.ibanfr.hibernate.model.User;
import com.ibanfr.manager.UserManager;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link UserManager}.
 * <p>
 * Use {@link #testInsertUsers()} to create sample user into db.
 */
public class UserManagerTest {
	
	@Test
	public void testListUsers() {
		
		UserManager manager = new UserManager();
		List<User> users = manager.listAllUsers();
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
	@Test
	public void testInsertUsers() {
		
			UserManager manager = new UserManager();
			manager.createUsers(2);
				
		
	}
	@Test
	public void testFindUserById() {
		
			UserManager manager = new UserManager();
			User u = manager.findUserById(1);
			System.out.println(u.toString());
			assertNotNull(u);
			assertEquals(1, u.getUserId());
		
	}

	@Test
	public void testSaveOrUpdateUser(){
		UserManager manager = new UserManager();
		User user = new User();
		user.setUsername("TestSaveOrUpdate");
		user.setCreatedDate(new Date());
		manager.saveOrUpdateUser(user);

		assertNotNull(user);
		System.out.println(user);

	}

}

package com.ibanfr.hibernate.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ibanfr.hibernate.model.User;
import com.ibanfr.manager.UserManager;


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
			User u = manager.findUserbyId(1);
			System.out.println(u.toString());
			assertNotNull(u);
			assertEquals(1, u.getUserId());
		
	}

}

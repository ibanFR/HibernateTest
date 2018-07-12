package com.ibanfr.hibernate.manager;

import java.util.List;

import com.ibanfr.hibernate.model.User;
import com.ibanfr.manager.UserManager5;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UserManager5Test extends TestCase {
	
	public void testListUsers() {
		
		UserManager5 manager = new UserManager5();
		List<User> users = manager.listAllUsers();
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
	
	public void testInsertUsers() {
		
		try {
			UserManager5 manager = new UserManager5();
			manager.createUsers(2);
				
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void testFindUserById() {
		
		try {
			UserManager5 manager = new UserManager5();
			User u = manager.findUserbyId(1);
			System.out.println(u.toString());
			Assert.assertNotNull(u);
			Assert.assertEquals(1, u.getUserId());
				
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

}

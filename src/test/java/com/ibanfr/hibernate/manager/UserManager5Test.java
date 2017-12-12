package com.ibanfr.hibernate.manager;

import java.util.List;

import com.ibanfr.hibernate.model.User;
import com.ibanfr.manager.UserManager5;

import junit.framework.TestCase;

public class UserManager5Test extends TestCase {
	
	public void testListUsers() {
		
		UserManager5 manager = new UserManager5();
		List<User> users = manager.listAllUsers();
		for (User user : users) {
			System.out.println(user.toString());
		}
	}

}

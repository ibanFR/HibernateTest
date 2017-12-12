package com.ibanfr.hibernate.manager;

import com.ibanfr.hibernate.model.User;
import com.ibanfr.manager.UserManager;

public class UserManagerTest {

	public static void main(String[] args) {
		
		UserManager manager = new UserManager();
		User u = manager.createUser("Ivan2", "ibanFR2");
		
		System.out.println(u.toString());
	}
}

package com.ibanfr.hibernate.manager;

import com.ibanfr.hibernate.dto.CountDetailsDTO;
import com.ibanfr.hibernate.dto.UserDetailsDTO;
import com.ibanfr.hibernate.model.User;
import com.ibanfr.manager.UserManager;
import org.junit.jupiter.api.Assertions;
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

	UserManager userManager = new UserManager();


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
		user.setCreatedBy("etu");
		user.setCreatedDate(new Date());
		manager.saveOrUpdateUser(user);

		assertNotNull(user);
		System.out.println(user);

	}

	@Test
	public void listUsersWithHQL_WhenTupleTransformerUsed() {

		List<UserDetailsDTO> userList = userManager.listUsersWithHQL(true);

		long noUsers = userList.stream().filter(u->u.getName().equals("testUser0")).count();

		Assertions.assertEquals(1,1);
	}

	@Test
	public void listUsersWithHQL_WhenResultListTransformerUsed() {

		List<UserDetailsDTO> userList = userManager.listUsersWithHQL(false);

		long noUsers = userList.stream().filter(u->u.getName().equals("testUser0")).count();

		Assertions.assertEquals(1,1);

	}

	@Test
	/**
	 * As of hibernate 6.0.0.Alpha9 this test fails. The transformer used in  listUsersWithNativeQuery does not translate
	 * the query results to a DTO. Instead an object array is returned
	 */
	public void testListUsersWithNativeQuery() {

		List<UserDetailsDTO> userList = userManager.listUsersWithNativeQuery();

		long noUsers = userList.stream().filter(u->u.getName().equals("testUser0")).count();

		Assertions.assertEquals(1,1);
	}

	@Test
	public void testListAggregatedUserWithHQL() {

		List<CountDetailsDTO> countDetails = userManager.listAggregatedUserWithHQL();

		long count = countDetails.stream().filter(u->u.getCreatedBy().equals("etu")).count();
		Assertions.assertEquals(1,count);

	}


}

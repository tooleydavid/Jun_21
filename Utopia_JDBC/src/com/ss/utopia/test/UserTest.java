package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.User;
import com.ss.utopia.service.AdminService;


public class UserTest {

	User user = new User();
	AdminService service = new AdminService();
	
	
	@Test
	public void testAirport() throws SQLException
	{
		
		//add
		user.setEmail("TEST");
		user.setFamily_name("TEST");
		user.setGiven_name("TEST");
		user.setPassword("TEST");
		user.setUsername("TEST");
		user.setRole_id(2);
		user.setPhone("TEST");
		assertEquals(0,service.readUser("TEST").size());
		service.addUser(user);
		assertEquals(1,service.readUser("TEST").size());
		user.setId(service.readUser("TEST").get(0).getId());
		
		//update
		user.setEmail("TEST2");
		service.updateUser(user);
		assertTrue("TEST2".equals(service.readUser("TEST").get(0).getEmail()));
		
		//delete
		service.deleteUser(user);
		assertEquals(0,service.readUser("TEST2").size());
				
	}

}
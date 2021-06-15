package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking_User;
import com.ss.utopia.service.AdminService;


public class Booking_UserTest {

	Booking_User user = new Booking_User();
	AdminService service = new AdminService();
	int id = 72;
	
	@Test
	public void testAirport() throws SQLException
	{
		
		//add
		user.setUser_id(service.readUser(1).get(0));
		user.setBooking_id(service.readBooking(id).get(0));
				
		
		assertEquals(0,service.readBooking_UserBooking(id).size());
		service.addBooking_User(user);
		assertEquals(1,service.readBooking_UserBooking(id).size());
				
		//update
		user.setUser_id(service.readUser(2).get(0));
		service.updateBooking_User(user);
		assertEquals(2,service.readBooking_UserBooking(id).get(0).getUser_id().getId());
		
		//delete
		service.deleteBooking_User(user);
		assertEquals(0,service.readBooking_UserBooking(id).size());
				
	}

}
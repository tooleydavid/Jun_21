package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking_Guest;
import com.ss.utopia.service.AdminService;


public class Booking_GuestTest {

	Booking_Guest guest = new Booking_Guest();
	AdminService service = new AdminService();
	int id = 72;
	
	@Test
	public void testAirport() throws SQLException
	{
		
		//add
		guest.setContact_email("TEST");
		guest.setContact_phone("TEST");
		guest.setBooking_id(service.readBooking(id).get(0));
				
		
		assertEquals(0,service.readBooking_Guest(id).size());
		service.addBooking_Guest(guest);
		assertEquals(1,service.readBooking_Guest(id).size());
				
		//update
		guest.setContact_email("TEST2");
		service.updateBooking_Guest(guest);
		assertTrue("TEST2".equals(service.readBooking_Guest(id).get(0).getContact_email()));
		
		//delete
		service.deleteBooking_Guest(guest);
		assertEquals(0,service.readBooking_Guest(id).size());
				
	}

}
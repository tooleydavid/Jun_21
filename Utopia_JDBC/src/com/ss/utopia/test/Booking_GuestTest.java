package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_Guest;
import com.ss.utopia.service.AdminService;


public class Booking_GuestTest {

	Booking_Guest guest = new Booking_Guest();
	AdminService service = new AdminService();
	Booking booking = new Booking();
	int id = 72;
	
	@Test
	public void testAirport() throws SQLException
	{
		booking.setConfirmation_code("TEST");
		booking.setId(50);
		booking.setIs_active(1);
		service.addBooking(booking);
		
		//add
		guest.setContact_email("TEST");
		guest.setContact_phone("TEST");
		guest.setBooking_id(booking);
				
		
		assertEquals(0,service.readBooking_Guest(booking.getId()).size());
		service.addBooking_Guest(guest);
		assertEquals(1,service.readBooking_Guest(booking.getId()).size());
				
		//update
		guest.setContact_email("TEST2");
		service.updateBooking_Guest(guest);
		assertTrue("TEST2".equals(service.readBooking_Guest(booking.getId()).get(0).getContact_email()));
		
		//delete
		service.deleteBooking_Guest(guest);
		assertEquals(0,service.readBooking_Guest(booking.getId()).size());
		
		service.deleteBooking(booking);
	}

}
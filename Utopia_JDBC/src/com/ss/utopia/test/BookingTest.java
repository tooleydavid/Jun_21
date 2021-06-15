package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.service.AdminService;


public class BookingTest {

	Booking booking = new Booking();
	AdminService service = new AdminService();
	
	
	@Test
	public void testAirport() throws SQLException
	{
		
		//add
		booking.setConfirmation_code("TESTTEST");
		booking.setIs_active(1);
		assertEquals(0,service.readBooking("TESTTEST").size());
		service.addBooking(booking);
		assertEquals(1,service.readBooking("TESTTEST").size());
		booking.setId(service.readBooking("TESTTEST").get(0).getId());
				
		//update
		booking.setIs_active(0);
		service.updateBooking(booking);
		assertEquals(0,service.readBooking(booking.getId()).get(0).getIs_active());
		
		//delete
		service.deleteBooking(booking);
		assertEquals(0,service.readBooking("TESTTEST").size());
				
	}

}
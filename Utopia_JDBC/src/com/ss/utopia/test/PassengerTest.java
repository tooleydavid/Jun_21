package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.service.AdminService;


public class PassengerTest {

	Passenger passenger = new Passenger();
	AdminService service = new AdminService();
	int id = 72;
	Booking booking = new Booking();
	
	@Test
	public void testAirport() throws SQLException
	{
		booking.setConfirmation_code("TEST");
		booking.setId(50);
		booking.setIs_active(1);
		service.addBooking(booking);
		
		//add
		passenger.setBooking_id(booking);
		passenger.setAddress("TEST");
		passenger.setDate("2020-12-12");
		passenger.setFamily_name("TEST");
		passenger.setGender("TEST");
		passenger.setGiven_name("TEST");
		
		
		assertEquals(0,service.readPassenger("TEST").size());
		service.addPassenger(passenger);
		assertEquals(1,service.readPassenger("TEST").size());
		passenger.setId(service.readPassenger("TEST").get(0).getId());
				
		//update
		passenger.setFamily_name("TEST2");;
		service.updatePassenger(passenger);
		assertTrue("TEST2".equals(service.readPassenger("TEST").get(0).getFamily_name()));
		
		//delete
		service.deletePassenger(passenger);
		assertEquals(0,service.readBookingPayment("TEST").size());
			
		service.deleteBooking(booking);
	}

}
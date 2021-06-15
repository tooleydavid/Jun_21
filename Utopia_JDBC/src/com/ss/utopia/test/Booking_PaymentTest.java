package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking_Payment;
import com.ss.utopia.service.AdminService;


public class Booking_PaymentTest {

	Booking_Payment btype = new Booking_Payment();
	AdminService service = new AdminService();
	
	
	@Test
	public void testAirport() throws SQLException
	{
		
		//add
		btype.setBooking_id(service.readBooking(72).get(0));
		btype.setRefunded(0);
		btype.setStripe_id("TEST");
		assertEquals(0,service.readBookingPayment("TEST").size());
		service.addBookingPayment(btype);
		assertEquals(1,service.readBookingPayment("TEST").size());
				
		//update
		btype.setStripe_id("TEST2");;
		service.updateBookingPayment(btype);
		assertTrue("TEST2".equals(service.readBookingPayment("TEST2").get(0).getStripe_id()));
		
		//delete
		service.deleteBookingPayment(btype);
		assertEquals(0,service.readBookingPayment("TEST").size());
				
	}

}
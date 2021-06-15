package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_Agent;
import com.ss.utopia.domain.User;
import com.ss.utopia.service.AdminService;


public class Booking_AgentTest {

	Booking_Agent agent = new Booking_Agent();
	AdminService service = new AdminService();
	int id = 72;
	Booking booking = new Booking();
	User u = new User();
	User u2 = new User();
	
	@Test
	public void testAirport() throws SQLException
	{
		booking.setConfirmation_code("TEST");
		booking.setId(50);
		booking.setIs_active(1);
		service.addBooking(booking);
		booking.setId(service.readBooking("TEST").get(0).getId());
		
		u.setEmail("TEST");
		u.setFamily_name("TEST");
		u.setGiven_name("TEST");
		u.setPassword("TEST");
		u.setUsername("TEST");
		u.setRole_id(2);
		u.setPhone("TEST");
		service.addUser(u);
		u.setId(service.readUser("TEST").get(0).getId());
		
		u2.setEmail("TEST2");
		u2.setFamily_name("TEST2");
		u2.setGiven_name("TEST2");
		u2.setPassword("TEST2");
		u2.setUsername("TEST2");
		u2.setRole_id(2);
		u2.setPhone("TEST2");
		service.addUser(u2);
		u2.setId(service.readUser("TEST2").get(0).getId());
		
		
		//add
		agent.setAgent_id(u);
		agent.setBooking_id(booking);
				
		
		assertEquals(0,service.readBooking_Agent(booking.getId()).size());
		service.addBooking_Agent(agent);
		assertEquals(1,service.readBooking_Agent(booking.getId()).size());
				
		//update
		agent.setAgent_id(u2);
		service.updateBooking_Agent(agent);
		assertEquals(u2.getId(),service.readBooking_Agent(booking.getId()).get(0).getAgent_id().getId());
		
		//delete
		service.deleteBooking_Agent(agent);
		assertEquals(0,service.readBooking_Agent(booking.getId()).size());
		
		service.deleteBooking(booking);
		service.deleteUser(u);
		service.deleteUser(u2);
				
	}

}
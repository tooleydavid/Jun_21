package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Booking_Agent;
import com.ss.utopia.service.AdminService;


public class Booking_AgentTest {

	Booking_Agent agent = new Booking_Agent();
	AdminService service = new AdminService();
	int id = 72;
	
	@Test
	public void testAirport() throws SQLException
	{
		
		//add
		agent.setAgent_id(service.readUser(1).get(0));
		agent.setBooking_id(service.readBooking(id).get(0));
				
		
		assertEquals(0,service.readBooking_Agent(id).size());
		service.addBooking_Agent(agent);
		assertEquals(1,service.readBooking_Agent(id).size());
				
		//update
		agent.setAgent_id(service.readUser(2).get(0));
		service.updateBooking_Agent(agent);
		assertEquals(2,service.readBooking_Agent(id).get(0).getAgent_id().getId());
		
		//delete
		service.deleteBooking_Agent(agent);
		assertEquals(0,service.readBooking_Agent(id).size());
				
	}

}
package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.service.AdminService;


public class AirportTest {

	Airport airport = new Airport();
	AdminService service = new AdminService();
	
	
	@Test
	public void testAirport() throws SQLException
	{
		
		airport.setAirportCode("tes");//Add
		airport.setCity("TEST");
		assertEquals(0,service.readAirports("tes").size());
		service.addAirport(airport);
		assertEquals(1,service.readAirports("tes").size());
		
		//update
		airport.setCity("TEST2");
		service.updateAirport(airport);
		assertTrue("TEST2".equals(service.readAirports("tes").get(0).getCity()));
		
		//delete
		service.deleteAirport(airport);
		assertEquals(0,service.readAirports("tes").size());
				
	}

}
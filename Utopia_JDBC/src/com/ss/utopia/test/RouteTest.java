package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Route;
import com.ss.utopia.service.AdminService;


public class RouteTest {

	Route route = new Route();
	AdminService service = new AdminService();
	Airport a1 = new Airport();
	Airport a2 = new Airport();
	Airport a3 = new Airport();
	
	
	@Test
	public void testAirport() throws SQLException
	{
		a1.setAirportCode("ts1");
		a1.setCity("TEST1");
		service.addAirport(a1);
		
		a2.setAirportCode("ts2");
		a2.setCity("TEST2");
		service.addAirport(a2);
		
		a3.setAirportCode("ts3");
		a3.setCity("TEST3");
		service.addAirport(a3);
		//add
		
		route.setDestinationAirport(a2);
		route.setOriginAirport(a1);
		assertEquals(0,service.readRoutes(route.getOriginAirport().getAirportCode(),route.getDestinationAirport().getAirportCode()).size());
		service.addRoute(route);
		assertEquals(1,service.readRoutes(route.getOriginAirport().getAirportCode(),route.getDestinationAirport().getAirportCode()).size());
		route.setId(service.readRoutes(route.getOriginAirport().getAirportCode(),route.getDestinationAirport().getAirportCode()).get(0).getId());
		
		//update
		route.setDestinationAirport(a3);
		service.updateRoute(route);
		assertTrue("ts3".equals(service.readRoutes(route.getId()).get(0).getDestinationAirport().getAirportCode()));
		
		//delete
		service.deleteRoute(route);
		assertEquals(0,service.readUser("TEST2").size());
		
		service.deleteAirport(a1);
		service.deleteAirport(a2);
		service.deleteAirport(a3);
				
	}

}
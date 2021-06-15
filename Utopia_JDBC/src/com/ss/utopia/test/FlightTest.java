package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Airplane_Type;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Route;
import com.ss.utopia.service.AdminService;


public class FlightTest {

	Flight flight = new Flight();
	AdminService service = new AdminService();
	Route route = new Route();
	Airport a1 = new Airport();
	Airport a2 = new Airport();
	Airplane air = new Airplane();
	Airplane_Type t1 = new Airplane_Type();
	
	int id = 45;
	
	@Test
	public void testAirport() throws SQLException
	{
		a1.setAirportCode("ts1");
		a1.setCity("TEST1");
		service.addAirport(a1);
		
		a2.setAirportCode("ts2");
		a2.setCity("TEST2");
		service.addAirport(a2);
		
		route.setDestinationAirport(a2);
		route.setOriginAirport(a1);
		service.addRoute(route);
		route.setId(service.readRoutes(a1.getAirportCode(), a2.getAirportCode()).get(0).getId());
		
		t1.setId(15);
		t1.setMax_capacity(20);
		service.addAirplaneType(t1);
		
		air.setType_id(t1);
		service.addAirplane(air);
		air.setId(service.readAirplaneByType(t1.getId()).get(0).getId());
		
		//add
		flight.setAirplane_id(air);
		flight.setId(id);
		flight.setDeparture_time("2020-12-12 12:12");
		flight.setReserved_seats(200);
		flight.setRoute_id(route);
		flight.setSeat_price(200);
		assertEquals(0,service.readFlight(id).size());
		service.addFlight(flight);
		assertEquals(1,service.readFlight(id).size());

		
		//update
		flight.setReserved_seats(300);
		service.updateFlight(flight);
		assertTrue(300 == service.readFlight(id).get(0).getReserved_seats());
		
		//delete
		service.deleteFlight(flight);
		assertEquals(0,service.readFlight(id).size());
		
		service.deleteAirplane(air);
		service.deleteAirplaneType(t1);
		
		service.deleteRoute(route);
		service.deleteAirport(a1);
		service.deleteAirport(a2);
		
				
	}

}
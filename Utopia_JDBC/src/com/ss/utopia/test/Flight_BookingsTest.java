package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Airplane_Type;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Flight_Bookings;
import com.ss.utopia.domain.Route;
import com.ss.utopia.service.AdminService;


public class Flight_BookingsTest {

	Flight_Bookings flight = new Flight_Bookings();
	AdminService service = new AdminService();
	
	Flight f = new Flight();
	Booking booking = new Booking();
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
		f.setAirplane_id(air);
		f.setId(id);
		f.setDeparture_time("2020-12-12 12:12");
		f.setReserved_seats(200);
		f.setRoute_id(route);
		f.setSeat_price(200);
		service.addFlight(f);
		
		booking.setConfirmation_code("TEST");
		booking.setIs_active(1);
		booking.setId(50);
		service.addBooking(booking);
				
		
		//add
		flight.setBooking_id(booking);
		flight.setFlight_id(f);
		
		assertEquals(0,service.readFlightBooking(booking.getId(),f.getId()).size());
		service.addFlightBooking(flight);
		assertEquals(1,service.readFlightBooking(booking.getId(),f.getId()).size());


		
		//update
		flight.setFlight_id(f);
		service.updateFlightBooking(flight);
		assertTrue(f.getId() == service.readFlightBooking(booking.getId(),f.getId()).get(0).getFlight_id().getId());
		
		//delete
		service.deleteFlightBooking(flight);
		assertEquals(0,service.readFlightBooking(1,2).size());
		
		service.deleteFlight(f);
		service.deleteBooking(booking);
		
		service.deleteAirplane(air);
		service.deleteAirplaneType(t1);
		
		service.deleteRoute(route);
		service.deleteAirport(a1);
		service.deleteAirport(a2);
				
	}

}
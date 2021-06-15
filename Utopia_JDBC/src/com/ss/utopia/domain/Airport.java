package com.ss.utopia.domain;

import java.util.List;

public class Airport {



	private String airportCode;
	private String city;
	private List<Route> routes;



	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	
	@Override
	public String toString() {
		return "Airport " + airportCode + ", " + city;
	}

}
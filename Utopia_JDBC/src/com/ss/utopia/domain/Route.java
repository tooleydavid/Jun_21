package com.ss.utopia.domain;

public class Route {
	
	private Integer id;
	private Airport originAirport;
	private Airport destinationAirport;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Airport getOriginAirport() {
		return originAirport;
	}
	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}
	public Airport getDestinationAirport() {
		return destinationAirport;
	}
	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	@Override
	public String toString() {
		return "Route [id=" + id + ", originAirport=" + originAirport.getAirportCode() + ", destinationAirport=" + destinationAirport.getAirportCode()
				+ "]";
	}

}
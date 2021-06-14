package com.ss.utopia.domain;

public class Flight {
	private int id;
	private Route route_id;
	private Airplane airplane_id;
	private String departure_time;
	private int reserved_seats;
	private float seat_price;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Route getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Route route_id) {
		this.route_id = route_id;
	}
	public Airplane getAirplane_id() {
		return airplane_id;
	}
	public void setAirplane_id(Airplane airplane_id) {
		this.airplane_id = airplane_id;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public int getReserved_seats() {
		return reserved_seats;
	}
	public void setReserved_seats(int reserved_seats) {
		this.reserved_seats = reserved_seats;
	}
	public float getSeat_price() {
		return seat_price;
	}
	public void setSeat_price(float seat_price) {
		this.seat_price = seat_price;
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", route_id=" + route_id.getId() + ", airplane_id=" + airplane_id.getId() + ", departure_time="
				+ departure_time + ", reserved_seats=" + reserved_seats + ", seat_price=" + seat_price + "]";
	}
	
	
	
}

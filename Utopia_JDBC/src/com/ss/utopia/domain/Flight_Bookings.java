package com.ss.utopia.domain;

public class Flight_Bookings {
	private Flight flight_id;
	private Booking booking_id;
	
	
	public Flight getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(Flight flight_id) {
		this.flight_id = flight_id;
	}
	public Booking getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Booking booking_id) {
		this.booking_id = booking_id;
	}
	@Override
	public String toString() {
		return "Booking_id=" + booking_id.getId() +" "+flight_id;
	}
	
	
}

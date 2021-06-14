package com.ss.utopia.domain;

public class Booking_User {
	@Override
	public String toString() {
		return "Booking_User [booking_id=" + booking_id.getId() + ", user_id=" + user_id.getId() + "]";
	}
	private Booking booking_id;
	private User user_id;
	
	
	public Booking getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Booking booking_id) {
		this.booking_id = booking_id;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User agent_id) {
		this.user_id = agent_id;
	}
	
	
}

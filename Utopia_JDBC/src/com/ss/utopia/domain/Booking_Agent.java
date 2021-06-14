package com.ss.utopia.domain;

public class Booking_Agent {
private Booking booking_id;
private User agent_id;


public Booking getBooking_id() {
	return booking_id;
}
public void setBooking_id(Booking booking_id) {
	this.booking_id = booking_id;
}
public User getAgent_id() {
	return agent_id;
}
public void setAgent_id(User agent_id) {
	this.agent_id = agent_id;
}

}

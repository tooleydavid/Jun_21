package com.ss.utopia.domain;

public class Booking_Payment {
	private Booking booking_id;
	private String stripe_id;
	private int refunded;
	
	
	
	public Booking getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Booking booking_id) {
		this.booking_id = booking_id;
	}
	public String getStripe_id() {
		return stripe_id;
	}
	public void setStripe_id(String stripe_id) {
		this.stripe_id = stripe_id;
	}
	public int getRefunded() {
		return refunded;
	}
	public void setRefunded(int refunded) {
		this.refunded = refunded;
	} 

}

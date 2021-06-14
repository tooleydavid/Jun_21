package com.ss.utopia.domain;

public class Passenger {
	private int id;
	private Booking booking_id;
	private String given_name;
	private String family_name;
	private String date;
	private String gender;
	private String address;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Booking getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Booking booking_id) {
		this.booking_id = booking_id;
	}
	public String getGiven_name() {
		return given_name;
	}
	@Override
	public String toString() {
		return "Passenger [id=" + id + ", booking_id=" + booking_id.getId() + ", given_name=" + given_name + ", family_name="
				+ family_name + ", date=" + date + ", gender=" + gender + ", address=" + address + "]";
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}

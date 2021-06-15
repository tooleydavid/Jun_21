package com.ss.utopia.domain;

public class Booking {
	
	private int id;
	private int is_active;
	private String confirmation_code;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public String getConfirmation_code() {
		return confirmation_code;
	}
	public void setConfirmation_code(String confirmation_code) {
		this.confirmation_code = confirmation_code;
	}
	
	@Override
	public String toString() {
		if(is_active == 1)
			return "Booking: " + id + ", is active with confirmation code: " + confirmation_code;
		else
			return "Booking: " + id + ", is inactive with confirmation code: " + confirmation_code;
	}
	
}

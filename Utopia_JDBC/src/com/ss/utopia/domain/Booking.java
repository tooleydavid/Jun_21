package com.ss.utopia.domain;

public class Booking {
	@Override
	public String toString() {
		return "Booking [id=" + id + ", is_active=" + is_active + ", confirmation_code=" + confirmation_code + "]";
	}
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
	
	
}

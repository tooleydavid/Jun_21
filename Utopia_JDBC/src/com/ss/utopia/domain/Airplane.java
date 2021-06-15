package com.ss.utopia.domain;

public class Airplane {
	private int id;
	private Airplane_Type type_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Airplane_Type getType_id() {
		return type_id;
	}
	public void setType_id(Airplane_Type type_id) {
		this.type_id = type_id;
	}
	@Override
	public String toString() {
		return "Airplane " + id + " with a max Capacity of "+ type_id.getMax_capacity();
	}
	
	

}

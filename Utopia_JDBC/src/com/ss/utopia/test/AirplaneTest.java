package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Airplane_Type;
import com.ss.utopia.service.AdminService;


public class AirplaneTest {

	Airplane airplane = new Airplane();
	AdminService service = new AdminService();
	Airplane_Type t1 = new Airplane_Type();
	Airplane_Type t2 = new Airplane_Type();
	int id = 1;
	
	@Test
	public void testAirport() throws SQLException
	{
		t1.setId(15);
		t1.setMax_capacity(200);
		service.addAirplaneType(t1);
		
		t2.setId(16);
		t2.setMax_capacity(300);
		service.addAirplaneType(t2);
		
		//add
		airplane.setType_id(t1);
				
		
		assertEquals(0,service.readAirplaneByType(t1.getId()).size());
		service.addAirplane(airplane);
		assertEquals(1,service.readAirplaneByType(t1.getId()).size());
		airplane.setId(service.readAirplaneByType(t1.getId()).get(0).getId());
				
		//update
		airplane.setType_id(t2);
		service.updateAirplane(airplane);
		assertEquals(t2.getId(),service.readAirplane(airplane.getId()).get(0).getType_id().getId());
		
		//delete
		service.deleteAirplane(airplane);
		assertEquals(0,service.readAirplane(airplane.getId()).size());
		
		service.deleteAirplaneType(t1);
		service.deleteAirplaneType(t2);
				
	}


}
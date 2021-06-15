package com.ss.utopia.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.domain.Airplane_Type;
import com.ss.utopia.service.AdminService;


public class Airplane_TypeTest {

	Airplane_Type atype = new Airplane_Type();
	AdminService service = new AdminService();
	
	
	@Test
	public void testAirport() throws SQLException
	{
		
		//add
		atype.setId(55);
		atype.setMax_capacity(2010111);
		assertEquals(0,service.readAirplaneType(55).size());
		service.addAirplaneType(atype);
		assertEquals(1,service.readAirplaneType(55).size());
				
		//update
		atype.setMax_capacity(2010);
		service.updateAirplaneType(atype);
		assertEquals(2010,service.readAirplaneType(55).get(0).getMax_capacity());
		
		//delete
		service.deleteAirplaneType(atype);
		assertEquals(0,service.readAirplaneType(55).size());
				
	}

}
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airport;

public class AirportDAO extends BaseDAO<Airport> {

	public AirportDAO(Connection conn) {
		super(conn);
	}

	public void addAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("insert into airport VALUES(?,?);", new Object[] { airport.getAirportCode(),airport.getCity() });
		
	}

	public void updateAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("update airport set city = ? where iata_id = ?",
				new Object[] {airport.getCity(),airport.getAirportCode() });
	}

	public void deleteAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("delete from airport where iata_id = ?", new Object[] { airport.getAirportCode() });
	}

	public List<Airport> readAll() throws ClassNotFoundException, SQLException {
		return read("select * from airport", null);
	}
	
	public List<Airport> readAirportByAirport_id(String id) throws ClassNotFoundException, SQLException {
		return read("select * from airport where iata_id = ?", new Object[] {id});
	}

	public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airport> airports = new ArrayList<>();
		while (rs.next()) {
			Airport airport = new Airport();
			airport.setAirportCode(rs.getString("iata_id"));
			airport.setCity(rs.getString("city"));
			airports.add(airport);
		}
		return airports;
	}

}
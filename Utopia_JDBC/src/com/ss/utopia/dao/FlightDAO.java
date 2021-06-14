package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Route;

public class FlightDAO extends BaseDAO<Flight> {

	public FlightDAO(Connection conn) {
		super(conn);
	}

	public void addFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("insert into flight(id,route_id,airplane_id,departure_time,reserved_seats,seat_price) VALUES(?,?,?,?,?,?)", new Object[] { flight.getId(),flight.getRoute_id().getId(),
				flight.getAirplane_id().getId(),flight.getDeparture_time(),flight.getReserved_seats(),flight.getSeat_price() });
	}

	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("update flight set route_id = ? , airplane_id = ? , departure_time = ? , reserved_seats = ? , seat_price = ? where id = ?",
				new Object[] {flight.getRoute_id().getId(),flight.getAirplane_id().getId(),flight.getDeparture_time(),flight.getReserved_seats(),flight.getSeat_price(),flight.getId() });
	}

	public void deleteFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("delete from flight where id = ?", new Object[] { flight.getId() });
	}

	public List<Flight> readAll() throws ClassNotFoundException, SQLException {
		return read("select * from flight", null);
	}
	
	public List<Flight> readFlightByFlight_id(int id) throws ClassNotFoundException, SQLException {
		return read("select * from flight where id = ?", new Object[] {id});
	}

	public List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<>();
		while (rs.next()) {
			Flight flight = new Flight();
			flight.setId(rs.getInt("id"));
			flight.setRoute_id(new Route());
			flight.setAirplane_id(new Airplane());
			flight.getRoute_id().setId(rs.getInt("route_id"));
			flight.getAirplane_id().setId(rs.getInt("airplane_id"));
			flight.setDeparture_time(rs.getString("departure_time"));
			flight.setReserved_seats(rs.getInt("reserved_seats"));
			flight.setSeat_price(rs.getFloat("seat_price"));
			flights.add(flight);
		}
		return flights;
	}

}
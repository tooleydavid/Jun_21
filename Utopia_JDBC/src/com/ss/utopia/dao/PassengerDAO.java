package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Passenger;

public class PassengerDAO extends BaseDAO<Passenger> {

	public PassengerDAO(Connection conn) {
		super(conn);
	}

	public void addPassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("insert into passenger VALUES(?,?,?,?,?,?,?)", new Object[] { passenger.getId(),passenger.getBooking_id().getId(),
				passenger.getGiven_name(),passenger.getFamily_name(),passenger.getDate(),passenger.getGender(),passenger.getAddress() });
	}

	public void updatePassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("update passenger set booking_id = ? , given_name = ? , family_name = ? , dob = ? , gender = ? "
				+ ", address = ? where id = ?",
				new Object[] {passenger.getBooking_id().getId(),passenger.getGiven_name(),passenger.getFamily_name(),passenger.getDate(),
						passenger.getGender(),passenger.getAddress(),passenger.getId() });
	}

	public void deletePassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("delete from passenger where id = ?", new Object[] { passenger.getId() });
	}

	public List<Passenger> readAllPassenger() throws ClassNotFoundException, SQLException {
		return read("select * from passenger", null);
	}
	
	public List<Passenger> readPassengerById(int id) throws ClassNotFoundException, SQLException {
		return read("select * from passenger where id = ?", new Object[] {id});
	}
	
	public List<Passenger> readPassengerById(String name) throws ClassNotFoundException, SQLException {
		return read("select * from passenger where given_name = ?", new Object[] {name});
	}

	public List<Passenger> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = new ArrayList<>();
		while (rs.next()) {
			Passenger passenger = new Passenger();
			passenger.setBooking_id(new Booking());
			passenger.getBooking_id().setId(rs.getInt("booking_id"));
			passenger.setId(rs.getInt("id"));
			passenger.setGiven_name(rs.getString("given_name"));
			passenger.setFamily_name(rs.getString("family_name"));
			passenger.setDate(rs.getString("dob"));
			passenger.setGender(rs.getString("gender"));
			passenger.setAddress(rs.getString("address"));
			passengers.add(passenger);
		}
		return passengers;
	}

}
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Flight_Bookings;

public class Flight_BookingDAO extends BaseDAO<Flight_Bookings> {

	public Flight_BookingDAO(Connection conn) {
		super(conn);
	}

	public void addFlight_Bookings(Flight_Bookings flight_bookings) throws ClassNotFoundException, SQLException {
		save("insert into flight_bookings VALUES(?,?)", new Object[] { flight_bookings.getFlight_id().getId(),flight_bookings.getBooking_id().getId() });
	}

	public void updateFlight_Bookings(Flight_Bookings flight_bookings) throws ClassNotFoundException, SQLException {
		save("update flight_bookings set flight_id = ? where booking_id = ?",
				new Object[] {flight_bookings.getFlight_id().getId(),flight_bookings.getBooking_id().getId() });
	}

	public void deleteFlight_Bookings(Flight_Bookings flight_bookings) throws ClassNotFoundException, SQLException {
		save("delete from flight_bookings where booking_id = ?", new Object[] { flight_bookings.getBooking_id().getId() });
	}

	public List<Flight_Bookings> readAllFlights() throws ClassNotFoundException, SQLException {
		return read("select * from flight_bookings", null);
	}
	
	public List<Flight_Bookings> readFlight_BookingsByBooking_id(int booking_id) throws ClassNotFoundException, SQLException {
		return read("select * from flight_bookings where booking_id = ?", new Object[] {booking_id});
	}
	public List<Flight_Bookings> readFlight_BookingsByBoth(int booking_id,int flight_id) throws ClassNotFoundException, SQLException {
		return read("select * from flight_bookings where booking_id = ? and flight_id = ?", new Object[] {booking_id,flight_id});
	}

	public List<Flight_Bookings> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight_Bookings> flight_bookingss = new ArrayList<>();
		while (rs.next()) {
			Flight_Bookings flight_bookings = new Flight_Bookings();
			flight_bookings.setBooking_id(new Booking());
			flight_bookings.setFlight_id(new Flight());
			flight_bookings.getBooking_id().setId(rs.getInt("booking_id"));
			flight_bookings.getFlight_id().setId(rs.getInt("flight_id"));
			flight_bookingss.add(flight_bookings);
		}
		return flight_bookingss;
	}

}
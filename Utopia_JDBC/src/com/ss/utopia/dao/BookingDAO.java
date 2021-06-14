package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;

public class BookingDAO extends BaseDAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	public void addBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("insert into booking VALUES(?,?,?)", new Object[] { booking.getId(),booking.getIs_active(),booking.getConfirmation_code() });
	}

	public void updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("update booking set is_active = ? , confirmation_code = ? where id = ?",
				new Object[] {booking.getIs_active(),booking.getConfirmation_code(),booking.getId() });
	}

	public void deleteBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("delete from booking where booking_id = ?", new Object[] { booking.getId() });
	}

	public List<Booking> readAll() throws ClassNotFoundException, SQLException {
		return read("select * from booking", null);
	}
	
	public List<Booking> readBookingByBooking_id(int id) throws ClassNotFoundException, SQLException {
		return read("select * from booking where id = ?", new Object[] {id});
	}
	
	public List<Booking> readBookingByBooking_id(String conf) throws ClassNotFoundException, SQLException {
		return read("select * from booking where confirmation_code = ?", new Object[] {conf});
	}

	public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking> bookings = new ArrayList<>();
		while (rs.next()) {
			Booking booking = new Booking();
			booking.setId(rs.getInt("id"));
			booking.setConfirmation_code(rs.getString("confirmation_code"));
			booking.setIs_active(rs.getInt("is_active"));
			bookings.add(booking);
		}
		return bookings;
	}

}
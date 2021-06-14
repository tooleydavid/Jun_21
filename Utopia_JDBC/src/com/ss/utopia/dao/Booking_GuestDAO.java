package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_Guest;

public class Booking_GuestDAO extends BaseDAO<Booking_Guest> {

	public Booking_GuestDAO(Connection conn) {
		super(conn);
	}

	public void addBooking_Guest(Booking_Guest booking_guest) throws ClassNotFoundException, SQLException {
		save("insert into booking_guest VALUES(?,?,?)", new Object[] { booking_guest.getBooking_id().getId(),booking_guest.getContact_email(),booking_guest.getContact_phone() });
	}

	public void updateBooking_Guest(Booking_Guest booking_guest) throws ClassNotFoundException, SQLException {
		save("update booking_guest set contact_email = ? , contact_phone = ? where booking_id = ?",
				new Object[] {booking_guest.getContact_email(),booking_guest.getContact_phone(),booking_guest.getBooking_id().getId() });
	}

	public void deleteBooking_Guest(Booking_Guest booking_guest) throws ClassNotFoundException, SQLException {
		save("delete from booking_guest where booking_id = ?", new Object[] { booking_guest.getBooking_id().getId() });
	}

	public List<Booking_Guest> readAll() throws ClassNotFoundException, SQLException {
		return read("select * from booking_guest", null);
	}
	
	public List<Booking_Guest> readBooking_GuestByBooking_Guest_id(int booking_id) throws ClassNotFoundException, SQLException {
		return read("select * from booking_guest where booking_id = ?", new Object[] {booking_id});
	}

	public List<Booking_Guest> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking_Guest> booking_guests = new ArrayList<>();
		while (rs.next()) {
			Booking_Guest booking_guest = new Booking_Guest();
			booking_guest.setBooking_id(new Booking());
			booking_guest.getBooking_id().setId(rs.getInt("booking_id"));
			booking_guest.setContact_phone(rs.getString("contact_phone"));
			booking_guest.setContact_email(rs.getString("contact_email"));
			booking_guests.add(booking_guest);
		}
		return booking_guests;
	}

}
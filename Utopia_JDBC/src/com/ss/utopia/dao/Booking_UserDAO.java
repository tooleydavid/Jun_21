package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_User;
import com.ss.utopia.domain.User;

public class Booking_UserDAO extends BaseDAO<Booking_User> {

	public Booking_UserDAO(Connection conn) {
		super(conn);
	}

	public void addBooking_User(Booking_User bookingUser) throws ClassNotFoundException, SQLException {
		save("insert into booking_user VALUES(?,?)", new Object[] { bookingUser.getBooking_id().getId(),bookingUser.getUser_id().getId() });
	}

	public void updateBooking_User(Booking_User bookingUser) throws ClassNotFoundException, SQLException {
		save("update booking_user set user_id = ? where booking_id = ?",
				new Object[] {bookingUser.getUser_id().getId(),bookingUser.getBooking_id().getId() });
	}

	public void deleteBooking_User(Booking_User bookingUser) throws ClassNotFoundException, SQLException {
		save("delete from booking_user where booking_id = ?", new Object[] { bookingUser.getBooking_id().getId() });
	}

	public List<Booking_User> readAllUser() throws ClassNotFoundException, SQLException {
		return read("select * from booking_user", null);
	}
	
	public List<Booking_User> readBooking_UserByBooking_id(int booking_id) throws ClassNotFoundException, SQLException {
		return read("select * from booking_user where booking_id = ?", new Object[] {booking_id});
	}
	
	public List<Booking_User> readBooking_UserByUser_id(int user_id) throws ClassNotFoundException, SQLException {
		return read("select * from booking_user where user_id = ?", new Object[] {user_id});
	}

	public List<Booking_User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking_User> bookingUsers = new ArrayList<>();
		while (rs.next()) {
			Booking_User bookingUser = new Booking_User();
			bookingUser.setBooking_id(new Booking());
			bookingUser.setUser_id(new User());
			bookingUser.getBooking_id().setId(rs.getInt("booking_id"));
			bookingUser.getUser_id().setId(rs.getInt("user_id"));
			bookingUsers.add(bookingUser);
		}
		return bookingUsers;
	}

}
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_Agent;
import com.ss.utopia.domain.User;

public class Booking_AgentDAO extends BaseDAO<Booking_Agent> {

	public Booking_AgentDAO(Connection conn) {
		super(conn);
	}

	public void addBooking_Agent(Booking_Agent bookingAgent) throws ClassNotFoundException, SQLException {
		save("insert into booking_agent VALUES(?,?)", new Object[] { bookingAgent.getBooking_id().getId(),bookingAgent.getAgent_id().getId() });
	}

	public void updateBooking_Agent(Booking_Agent bookingAgent) throws ClassNotFoundException, SQLException {
		save("update booking_agent set agent_id = ? where booking_id = ?",
				new Object[] {bookingAgent.getAgent_id().getId(),bookingAgent.getBooking_id().getId() });
	}

	public void deleteBooking_Agent(Booking_Agent bookingAgent) throws ClassNotFoundException, SQLException {
		save("delete from booking_agent where booking_id = ?", new Object[] { bookingAgent.getBooking_id().getId() });
	}

	public List<Booking_Agent> readAllUser() throws ClassNotFoundException, SQLException {
		return read("select * from booking_agent", null);
	}
	
	public List<Booking_Agent> readBooking_AgentByBooking_id(int booking_id) throws ClassNotFoundException, SQLException {
		return read("select * from booking_agent where booking_id = ?", new Object[] {booking_id});
	}

	public List<Booking_Agent> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking_Agent> bookingAgents = new ArrayList<>();
		while (rs.next()) {
			Booking_Agent bookingAgent = new Booking_Agent();
			bookingAgent.setAgent_id(new User());
			bookingAgent.setBooking_id(new Booking());
			bookingAgent.getBooking_id().setId(rs.getInt("booking_id"));
			bookingAgent.getAgent_id().setId(rs.getInt("agent_id"));
			bookingAgents.add(bookingAgent);
		}
		return bookingAgents;
	}

}
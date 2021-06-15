package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_Payment;

public class Booking_PaymentDAO extends BaseDAO<Booking_Payment> {

	public Booking_PaymentDAO(Connection conn) {
		super(conn);
	}

	public void addBooking_Payment(Booking_Payment booking_payment) throws ClassNotFoundException, SQLException {
		save("insert into booking_payment VALUES(?,?,?)", new Object[] { booking_payment.getBooking_id().getId(),booking_payment.getStripe_id(),booking_payment.getRefunded() });
	}

	public void updateBooking_Payment(Booking_Payment booking_payment) throws ClassNotFoundException, SQLException {
		save("update booking_payment set stripe_id = ? , refunded = ? where booking_id = ?",
				new Object[] {booking_payment.getStripe_id(),booking_payment.getRefunded(),booking_payment.getBooking_id().getId() });
	}

	public void deleteBooking_Payment(Booking_Payment booking_payment) throws ClassNotFoundException, SQLException {
		save("delete from booking_payment where booking_id = ?", new Object[] { booking_payment.getBooking_id().getId() });
	}

	public List<Booking_Payment> readAll() throws ClassNotFoundException, SQLException {
		return read("select * from booking_payment", null);
	}
	
	public List<Booking_Payment> readBooking_PaymentByBooking_Payment_id(int booking_id) throws ClassNotFoundException, SQLException {
		return read("select * from booking_payment where booking_id = ?", new Object[] {booking_id});
	}
	public List<Booking_Payment> readBooking_PaymentByBooking_Payment_id(String id) throws ClassNotFoundException, SQLException {
		return read("select * from booking_payment where stripe_id = ?", new Object[] {id});
	}

	public List<Booking_Payment> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking_Payment> booking_payments = new ArrayList<>();
		while (rs.next()) {
			Booking_Payment booking_payment = new Booking_Payment();
			booking_payment.setBooking_id(new Booking());
			booking_payment.getBooking_id().setId(rs.getInt("booking_id"));
			booking_payment.setStripe_id(rs.getString("stripe_id"));
			booking_payment.setRefunded(rs.getInt("refunded"));
			booking_payments.add(booking_payment);
		}
		return booking_payments;
	}

}
package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.Booking_PaymentDAO;
import com.ss.utopia.dao.Booking_UserDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.Flight_BookingDAO;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_Payment;
import com.ss.utopia.domain.Booking_User;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Flight_Bookings;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.Route;
import com.ss.utopia.domain.User;

public class AdminService {

	ConnectionUtil connUtil = new ConnectionUtil();

	// -----------------------------------------------------ROUTE
	public void addRoute(Route route) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			AirportDAO adao = new AirportDAO(conn);
			Airport airport;
			String city;
			Scanner scan = new Scanner(System.in);

			List<Airport> airportList = readAirports(route.getOriginAirport().getAirportCode());
			if (airportList == null || airportList.size() < 1) {
				System.out.println(
						"No Airport was found for the origin id, a new will be created\nPlease enter a city name: ");
				city = scan.nextLine();
				airport = new Airport();
				airport.setAirportCode(route.getOriginAirport().getAirportCode());
				airport.setCity(city);
				adao = new AirportDAO(conn);
				adao.addAirport(airport);

			}
			airportList = readAirports(route.getDestinationAirport().getAirportCode());
			if (airportList == null || airportList.size() < 1) {
				System.out.println(
						"No Airport was found for the destination id, a new will be created\nPlease enter a city name: ");
				city = scan.nextLine();
				airport = new Airport();
				airport.setAirportCode(route.getDestinationAirport().getAirportCode());
				airport.setCity(city);
				adao = new AirportDAO(conn);
				adao.addAirport(airport);

			}

			RouteDAO rdao = new RouteDAO(conn);
			rdao.addRoute(route);

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public List<Route> readRoutes() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			List<Route> routes = rdao.readAllRoutes();
			// populate the child elements here.
			return routes;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Route> readRoutes(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			List<Route> routes = rdao.readRoutesById(id);
			return routes;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Route> readRoutes(String origin, String dest) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			List<Route> routes = rdao.readRoutesByAirportCodes(origin, dest);
			return routes;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	// ---------------------------------------------------------------AIRPORT
	public List<Airport> readAirports() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			List<Airport> airport = adao.readAll();
			// populate the child elements here.
			return airport;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Airport> readAirports(String id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			List<Airport> airport = adao.readAirportByAirport_id(id);
			// populate the child elements here.
			return airport;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void addAirport(Airport airport) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.addAirport(airport);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void deleteAirport(Airport airport) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.deleteAirport(airport);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void updateAirport(Airport airport) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.updateAirport(airport);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------Traveler
	public List<User> readUser() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> user = udao.readAllUser();
			// populate the child elements here.
			return user;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<User> readUser(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> user = udao.readUserById(id);
			// populate the child elements here.
			return user;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void addUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.addUser(user);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void deleteUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.deleteUser(user);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void updateUser(User user) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.updateUser(user);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------Passenger
	public List<Passenger> readPassenger() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			List<Passenger> passenger = pdao.readAllPassenger();
			// populate the child elements here.
			return passenger;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Passenger> readPassenger(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			List<Passenger> passenger = pdao.readPassengerById(id);
			// populate the child elements here.
			return passenger;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void addPassenger(Passenger passenger) throws SQLException {
		Connection conn = null;
		try {
			Booking booking = new Booking();

			conn = connUtil.getConnection();
			List<Booking> bookinglist = readBooking(passenger.getBooking_id().getId());
			if (bookinglist == null || bookinglist.size() < 1) {
				System.out.println("No booking was found with that id a new one was created");
				int confcode = (int) (Math.random() * 9999 + 1);
				booking = new Booking();
				booking.setId(passenger.getBooking_id().getId());
				booking.setIs_active(1);
				booking.setConfirmation_code("Conf" + confcode);
				BookingDAO bdao = new BookingDAO(conn);
				bdao.addBooking(booking);

			} else
				booking = bookinglist.get(0);
			passenger.setBooking_id(booking);

			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.addPassenger(passenger);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void updatePassenger(Passenger passenger) throws SQLException {
		Connection conn = null;
		try {
			Booking booking = new Booking();

			conn = connUtil.getConnection();
			List<Booking> bookinglist = readBooking(passenger.getBooking_id().getId());
			if (bookinglist == null || bookinglist.size() < 1) {
				System.out.println("No booking was found with that id a new one was created");
				int confcode = (int) (Math.random() * 9999 + 1);
				booking = new Booking();
				booking.setId(passenger.getBooking_id().getId());
				booking.setIs_active(1);
				booking.setConfirmation_code("Conf" + confcode);
				BookingDAO bdao = new BookingDAO(conn);
				bdao.addBooking(booking);

			} else
				booking = bookinglist.get(0);
			passenger.setBooking_id(booking);

			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.updatePassenger(passenger);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void deletePassenger(Passenger passenger) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.deletePassenger(passenger);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

//----------------------------------------------------------Booking	
	public List<Booking> readBooking(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			List<Booking> booking = bdao.readBookingByBooking_id(id);
			// populate the child elements here.
			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}
	public List<Booking> readBooking(String conf) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			List<Booking> booking = bdao.readBookingByBooking_id(conf);
			// populate the child elements here.
			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Booking> readBooking() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			List<Booking> booking = bdao.readAll();
			// populate the child elements here.
			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void addBooking(Booking booking) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.addBooking(booking);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void updateBooking(Booking booking) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.updateBooking(booking);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------Booking_Payment
	public List<Booking_Payment> readBookingPayment(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_PaymentDAO bdao = new Booking_PaymentDAO(conn);
			List<Booking_Payment> booking = bdao.readBooking_PaymentByBooking_Payment_id(id);
			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void updateBookingPayment(Booking_Payment bookingpayment) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_PaymentDAO bdao = new Booking_PaymentDAO(conn);
			bdao.updateBooking_Payment(bookingpayment);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------Flight
	public List<Flight> readFlight() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			List<Flight> flight = fdao.readAll();
			// populate the child elements here.
			return flight;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Flight> readFlight(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			List<Flight> flight = fdao.readFlightByFlight_id(id);
			// populate the child elements here.
			return flight;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void addFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.addFlight(flight);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void deleteFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.deleteFlight(flight);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void updateFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.updateFlight(flight);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ----------------------------------------------------------------------Flight
	// Booking
	public void addFlightBooking(Flight_Bookings flight_booking) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight_BookingDAO fdao = new Flight_BookingDAO(conn);
			fdao.addFlight_Bookings(flight_booking);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public List<Flight_Bookings> readFlightBooking() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight_BookingDAO fdao = new Flight_BookingDAO(conn);
			List<Flight_Bookings> flight = fdao.readAllFlights();
			return flight;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Flight_Bookings> readFlightBooking(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight_BookingDAO fdao = new Flight_BookingDAO(conn);
			List<Flight_Bookings> flight = fdao.readFlight_BookingsByBooking_id(id);
			return flight;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void updateFlightBooking(Flight_Bookings flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight_BookingDAO fdao = new Flight_BookingDAO(conn);
			fdao.updateFlight_Bookings(flight);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void deleteFlightBooking(Flight_Bookings flight) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight_BookingDAO fdao = new Flight_BookingDAO(conn);
			fdao.deleteFlight_Bookings(flight);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ----------------------------------------------------------------------Airplane
	public List<Airplane> readAirplane() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			List<Airplane> airplane = adao.readAll();
			return airplane;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Airplane> readAirplane(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			List<Airplane> airplane = adao.readAirplaneByAirplane_id(id);
			return airplane;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	// ---------------------------------------------------------------------------------Traveler
	public void bookTicket(Flight_Bookings flight_bookings,Booking booking,User user) throws SQLException
	{

		Connection conn = null;
		try {
			conn = connUtil.getConnection();
		
			Flight_BookingDAO fbdao = new Flight_BookingDAO(conn);
			fbdao.addFlight_Bookings(flight_bookings);
			
			//add booking user
			Booking_User buser = new Booking_User();
			buser.setUser_id(user);
			buser.setBooking_id(booking);
			Booking_UserDAO budao= new Booking_UserDAO(conn);
			budao.addBooking_User(buser);
			
			conn.commit();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}	
	}
	public List<Booking_User> readBooking_User(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_UserDAO bdao = new Booking_UserDAO(conn);
			List<Booking_User> bookinguser = bdao.readBooking_UserByUser_id(id);
			return bookinguser;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}
	public List<Booking_User> deleteBooking_User(Booking_User bookinguser) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_UserDAO fdao = new Booking_UserDAO(conn);
			fdao.deleteBooking_User(bookinguser);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

}

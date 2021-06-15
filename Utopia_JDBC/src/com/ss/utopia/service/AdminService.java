package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.Airplane_TypeDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.Booking_AgentDAO;
import com.ss.utopia.dao.Booking_GuestDAO;
import com.ss.utopia.dao.Booking_PaymentDAO;
import com.ss.utopia.dao.Booking_UserDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.Flight_BookingDAO;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Airplane_Type;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Booking_Agent;
import com.ss.utopia.domain.Booking_Guest;
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

			route.setOriginAirport(readAirports(route.getOriginAirport().getAirportCode()).get(0));
			route.setDestinationAirport(readAirports(route.getDestinationAirport().getAirportCode()).get(0));

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
			Route r;
			for (int i = 0; i < routes.size(); i++) {
				r = routes.get(i);
				r.setOriginAirport(readAirports(r.getOriginAirport().getAirportCode()).get(0));
				r.setDestinationAirport(readAirports(r.getDestinationAirport().getAirportCode()).get(0));
				routes.set(i, r);
			}

			return routes;
		} catch (Exception e) {
			e.printStackTrace();
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

			Route r;
			for (int i = 0; i < routes.size(); i++) {
				r = routes.get(i);
				r.setOriginAirport(readAirports(r.getOriginAirport().getAirportCode()).get(0));
				r.setDestinationAirport(readAirports(r.getDestinationAirport().getAirportCode()).get(0));
				routes.set(i, r);
			}

			return routes;
		} catch (Exception e) {
			e.printStackTrace();
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

			Route r;
			for (int i = 0; i < routes.size(); i++) {
				r = routes.get(i);
				r.setOriginAirport(readAirports(r.getOriginAirport().getAirportCode()).get(0));
				r.setDestinationAirport(readAirports(r.getDestinationAirport().getAirportCode()).get(0));
				routes.set(i, r);
			}

			return routes;
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}

	public void deleteRoute(Route route) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			RouteDAO adao = new RouteDAO(conn);
			adao.deleteRoute(route);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void updateRoute(Route route) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			route.setOriginAirport(readAirports(route.getOriginAirport().getAirportCode()).get(0));
			route.setDestinationAirport(readAirports(route.getDestinationAirport().getAirportCode()).get(0));

			RouteDAO adao = new RouteDAO(conn);
			adao.updateRoute(route);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------AIRPORT
	public List<Airport> readAirports() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			List<Airport> airport = adao.readAll();

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

	// ---------------------------------------------------------------User
	public List<User> readUser() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> user = udao.readAllUser();
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
			return user;
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<User> readUser(String username) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> user = udao.readUserByUsername(username);
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

			Passenger p;
			for (int i = 0; i < passenger.size(); i++) {
				p = passenger.get(i);
				p.setBooking_id(readBooking(p.getBooking_id().getId()).get(0));
				passenger.set(i, p);
			}

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

			Passenger p;
			for (int i = 0; i < passenger.size(); i++) {
				p = passenger.get(i);
				p.setBooking_id(readBooking(p.getBooking_id().getId()).get(0));
				passenger.set(i, p);
			}

			return passenger;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Passenger> readPassenger(String name) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			List<Passenger> passenger = pdao.readPassengerById(name);

			Passenger p;
			for (int i = 0; i < passenger.size(); i++) {
				p = passenger.get(i);
				p.setBooking_id(readBooking(p.getBooking_id().getId()).get(0));
				passenger.set(i, p);
			}

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

			} else {
				booking = bookinglist.get(0);
				booking.setIs_active(1);
				BookingDAO bdao = new BookingDAO(conn);
				bdao.updateBooking(booking);
			}
			passenger.setBooking_id(booking);

			passenger.setBooking_id(readBooking(passenger.getBooking_id().getId()).get(0));

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

			passenger.setBooking_id(readBooking(passenger.getBooking_id().getId()).get(0));

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

	public void deleteBooking(Booking booking) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.deleteBooking(booking);
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

			Booking_Payment p;
			for (int i = 0; i < booking.size(); i++) {
				p = booking.get(i);
				p.setBooking_id(readBooking(p.getBooking_id().getId()).get(0));
				booking.set(i, p);
			}

			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Booking_Payment> readBookingPayment(String id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_PaymentDAO bdao = new Booking_PaymentDAO(conn);
			List<Booking_Payment> booking = bdao.readBooking_PaymentByBooking_Payment_id(id);

			Booking_Payment p;
			for (int i = 0; i < booking.size(); i++) {
				p = booking.get(i);
				p.setBooking_id(readBooking(p.getBooking_id().getId()).get(0));
				booking.set(i, p);
			}

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

			bookingpayment.setBooking_id(readBooking(bookingpayment.getBooking_id().getId()).get(0));

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

	public void addBookingPayment(Booking_Payment bookingpayment) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			bookingpayment.setBooking_id(readBooking(bookingpayment.getBooking_id().getId()).get(0));

			Booking_PaymentDAO bdao = new Booking_PaymentDAO(conn);
			bdao.addBooking_Payment(bookingpayment);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void deleteBookingPayment(Booking_Payment bookingpayment) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			Booking_PaymentDAO bdao = new Booking_PaymentDAO(conn);
			bdao.deleteBooking_Payment(bookingpayment);
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
			Flight f;
			for (int i = 0; i < flight.size(); i++) {
				f = flight.get(i);
				f.setRoute_id(readRoutes(f.getRoute_id().getId()).get(0));
				f.setAirplane_id(readAirplane(f.getAirplane_id().getId()).get(0));
				flight.set(i, f);
			}
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

			Flight f;
			for (int i = 0; i < flight.size(); i++) {
				f = flight.get(i);
				f.setRoute_id(readRoutes(f.getRoute_id().getId()).get(0));
				f.setAirplane_id(readAirplane(f.getAirplane_id().getId()).get(0));
				flight.set(i, f);
			}

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

			flight.setRoute_id(readRoutes(flight.getRoute_id().getId()).get(0));// Gets the correct Route and populates
																				// flight
			flight.setAirplane_id(readAirplane(flight.getAirplane_id().getId()).get(0));

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

			flight.setRoute_id(readRoutes(flight.getRoute_id().getId()).get(0));
			flight.setAirplane_id(readAirplane(flight.getAirplane_id().getId()).get(0));

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ----------------------------------------------------------------FlightBooking
	public void addFlightBooking(Flight_Bookings flight_booking) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			flight_booking.setBooking_id(readBooking(flight_booking.getBooking_id().getId()).get(0));
			flight_booking.setFlight_id(readFlight(flight_booking.getFlight_id().getId()).get(0));

			Flight_BookingDAO fdao = new Flight_BookingDAO(conn);
			fdao.addFlight_Bookings(flight_booking);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			// e.printStackTrace();
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

			Flight_Bookings flight_booking;
			for (int i = 0; i < flight.size(); i++) {
				flight_booking = flight.get(i);
				flight_booking.setBooking_id(readBooking(flight_booking.getBooking_id().getId()).get(0));
				flight_booking.setFlight_id(readFlight(flight_booking.getFlight_id().getId()).get(0));
				flight.set(i, flight_booking);
			}

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

			Flight_Bookings flight_booking;
			for (int i = 0; i < flight.size(); i++) {
				flight_booking = flight.get(i);
				flight_booking.setBooking_id(readBooking(flight_booking.getBooking_id().getId()).get(0));
				flight_booking.setFlight_id(readFlight(flight_booking.getFlight_id().getId()).get(0));
				flight.set(i, flight_booking);
			}

			return flight;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Flight_Bookings> readFlightBooking(int id, int fid) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Flight_BookingDAO fdao = new Flight_BookingDAO(conn);
			List<Flight_Bookings> flight = fdao.readFlight_BookingsByBoth(id, fid);

			Flight_Bookings flight_booking;
			for (int i = 0; i < flight.size(); i++) {
				flight_booking = flight.get(i);
				flight_booking.setBooking_id(readBooking(flight_booking.getBooking_id().getId()).get(0));
				flight_booking.setFlight_id(readFlight(flight_booking.getFlight_id().getId()).get(0));
				flight.set(i, flight_booking);
			}

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

			flight.setBooking_id(readBooking(flight.getBooking_id().getId()).get(0));
			flight.setFlight_id(readFlight(flight.getFlight_id().getId()).get(0));

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			// e.printStackTrace();
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

	// ----------------------------------------------------------------------AirplaneType
	public List<Airplane_Type> readAirplaneType(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Airplane_TypeDAO adao = new Airplane_TypeDAO(conn);
			List<Airplane_Type> airplane = adao.readAirplane_TypeByAirplane_Type_id(id);
			return airplane;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void addAirplaneType(Airplane_Type air) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			Airplane_TypeDAO adao = new Airplane_TypeDAO(conn);
			adao.addAirplane_Type(air);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void updateAirplaneType(Airplane_Type air) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			Airplane_TypeDAO adao = new Airplane_TypeDAO(conn);
			adao.updateAirplane_Type(air);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void deleteAirplaneType(Airplane_Type air) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			Airplane_TypeDAO adao = new Airplane_TypeDAO(conn);
			adao.deleteAirplane_Type(air);
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

			Airplane a;
			for (int i = 0; i < airplane.size(); i++) {
				a = airplane.get(i);
				a.setType_id(readAirplaneType(a.getType_id().getId()).get(0));
				airplane.set(i, a);
			}
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

			Airplane a;
			for (int i = 0; i < airplane.size(); i++) {
				a = airplane.get(i);
				a.setType_id(readAirplaneType(a.getType_id().getId()).get(0));
				airplane.set(i, a);
			}

			return airplane;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}
	public List<Airplane> readAirplaneByType(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			List<Airplane> airplane = adao.readAirplaneByType(id);

			Airplane a;
			for (int i = 0; i < airplane.size(); i++) {
				a = airplane.get(i);
				a.setType_id(readAirplaneType(a.getType_id().getId()).get(0));
				airplane.set(i, a);
			}

			return airplane;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}
	public void addAirplane(Airplane airplane) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			airplane.setType_id(readAirplaneType(airplane.getType_id().getId()).get(0));

			AirplaneDAO budao = new AirplaneDAO(conn);
			budao.addAirplane(airplane);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void updateAirplane(Airplane airplane) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			airplane.setType_id(readAirplaneType(airplane.getType_id().getId()).get(0));

			AirplaneDAO budao = new AirplaneDAO(conn);
			budao.updateAirplane(airplane);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void deleteAirplane(Airplane airplane) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			AirplaneDAO budao = new AirplaneDAO(conn);
			budao.deleteAirplane(airplane);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------------------------Traveler
	public void bookTicket(Flight_Bookings flight_bookings, Booking booking, User user) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			flight_bookings.setBooking_id(readBooking(flight_bookings.getBooking_id().getId()).get(0));
			flight_bookings.setFlight_id(readFlight(flight_bookings.getFlight_id().getId()).get(0));

			Flight_BookingDAO fbdao = new Flight_BookingDAO(conn);
			fbdao.addFlight_Bookings(flight_bookings);

			// add booking user
			Booking_User buser = new Booking_User();
			buser.setUser_id(user);
			buser.setBooking_id(booking);

			buser.setBooking_id(readBooking(buser.getBooking_id().getId()).get(0));
			buser.setUser_id(readUser(buser.getUser_id().getId()).get(0));

			Booking_UserDAO budao = new Booking_UserDAO(conn);
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

			Booking_User b;
			for (int i = 0; i < bookinguser.size(); i++) {
				b = bookinguser.get(i);
				b.setBooking_id(readBooking(b.getBooking_id().getId()).get(0));
				b.setUser_id(readUser(b.getUser_id().getId()).get(0));
				bookinguser.set(i, b);
			}

			return bookinguser;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Booking_User> readBooking_UserBooking(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_UserDAO bdao = new Booking_UserDAO(conn);
			List<Booking_User> bookinguser = bdao.readBooking_UserByBooking_id(id);

			Booking_User b;
			for (int i = 0; i < bookinguser.size(); i++) {
				b = bookinguser.get(i);
				b.setBooking_id(readBooking(b.getBooking_id().getId()).get(0));
				b.setUser_id(readUser(b.getUser_id().getId()).get(0));
				bookinguser.set(i, b);
			}

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

	public void updateBooking_User(Booking_User booking) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			booking.setBooking_id(readBooking(booking.getBooking_id().getId()).get(0));
			booking.setUser_id(readUser(booking.getUser_id().getId()).get(0));

			Booking_UserDAO budao = new Booking_UserDAO(conn);
			budao.updateBooking_User(booking);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void addBooking_User(Booking_User booking) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			booking.setBooking_id(readBooking(booking.getBooking_id().getId()).get(0));
			booking.setUser_id(readUser(booking.getUser_id().getId()).get(0));

			Booking_UserDAO budao = new Booking_UserDAO(conn);
			budao.addBooking_User(booking);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------------Booking_Agent
	public List<Booking_Agent> readBooking_Agent(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_AgentDAO bdao = new Booking_AgentDAO(conn);
			List<Booking_Agent> booking = bdao.readBooking_AgentByBooking_id(id);

			Booking_Agent b;
			for (int i = 0; i < booking.size(); i++) {
				b = booking.get(i);
				b.setBooking_id(readBooking(b.getBooking_id().getId()).get(0));
				b.setAgent_id(readUser(b.getAgent_id().getId()).get(0));
				booking.set(i, b);
			}

			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public List<Booking_Agent> deleteBooking_Agent(Booking_Agent bookingagent) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_AgentDAO fdao = new Booking_AgentDAO(conn);
			fdao.deleteBooking_Agent(bookingagent);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void addBooking_Agent(Booking_Agent booking) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			booking.setBooking_id(readBooking(booking.getBooking_id().getId()).get(0));
			booking.setAgent_id(readUser(booking.getAgent_id().getId()).get(0));

			Booking_AgentDAO budao = new Booking_AgentDAO(conn);
			budao.addBooking_Agent(booking);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void updateBooking_Agent(Booking_Agent booking) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			booking.setBooking_id(readBooking(booking.getBooking_id().getId()).get(0));
			booking.setAgent_id(readUser(booking.getAgent_id().getId()).get(0));

			Booking_AgentDAO budao = new Booking_AgentDAO(conn);
			budao.updateBooking_Agent(booking);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	// ---------------------------------------------------------------------Booking_Guest
	public List<Booking_Guest> readBooking_Guest(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_GuestDAO bdao = new Booking_GuestDAO(conn);
			List<Booking_Guest> booking = bdao.readBooking_GuestByBooking_Guest_id(id);

			Booking_Guest b;
			for (int i = 0; i < booking.size(); i++) {
				b = booking.get(i);
				b.setBooking_id(readBooking(b.getBooking_id().getId()).get(0));
				booking.set(i, b);
			}

			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return null;
	}

	public void deleteBooking_Guest(Booking_Guest booking) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			Booking_GuestDAO fdao = new Booking_GuestDAO(conn);
			fdao.deleteBooking_Guest(booking);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void addBooking_Guest(Booking_Guest booking) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			booking.setBooking_id(readBooking(booking.getBooking_id().getId()).get(0));

			Booking_GuestDAO budao = new Booking_GuestDAO(conn);
			budao.addBooking_Guest(booking);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void updateBooking_Guest(Booking_Guest booking) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();

			booking.setBooking_id(readBooking(booking.getBooking_id().getId()).get(0));

			Booking_GuestDAO budao = new Booking_GuestDAO(conn);
			budao.updateBooking_Guest(booking);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

}

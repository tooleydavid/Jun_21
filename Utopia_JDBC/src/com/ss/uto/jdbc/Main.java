/**
 * 
 */
package com.ss.uto.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import com.ss.utopia.service.AdminService;
import com.ss.utopia.service.ConnectionUtil;

/**
 * @author David Tooley Main file for Utopia
 */
public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println(
				"Welcome to the Utopia Airlines Management System. Which category of a user are you \n\t1)Administrator \n\t2)Traveler \n\t3)Employee");
		System.out.print("Enter the number here:  ");
		Scanner scan = new Scanner(System.in);
		List<User> userList;

		String input = "";
		int userRole = 0;
		while (true) // loops until user gives proper input
		{
			try {
				input = scan.nextLine();
				userRole = Integer.parseInt(input);
				if (userRole > 3 || userRole < 1) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.out.println("Please enter a number from 1-3");
			}
		}
		User user;
		if(userRole <0)
		{
			UserDAO userDao = new UserDAO(new ConnectionUtil().getConnection());

			while (true)// loops until user gives login
			{
				try {
					System.out.print("Username: ");
					input = scan.nextLine();
					userList = userDao.readUserByUsername(input);
					if (userList.size() < 1)
						throw new Exception();
					user = userList.get(0);
					System.out.println(user);
					System.out.print("Password: ");
					input = scan.nextLine();
					if (!input.equalsIgnoreCase(user.getPassword()) || userRole != user.getRole_id()) {
						throw new Exception();
					}
					break;
				} catch (Exception e) {
					System.out.println("Error Logging in");
					// e.printStackTrace();
				}
			}
		}
		else{
			UserDAO userDao = new UserDAO(new ConnectionUtil().getConnection());
			user = userDao.readUserByUsername("Man_of_war").get(0);
		}

		while (true) {
			switch (userRole) {
			case 1: // Admin
				int selection = 0;
				System.out.println("\nPlease make a choice: ");
				System.out.println("\t1) Add/Update/Delete/Read Flights");
				System.out.println("\t2) Add/Update/Delete/Read Seats");
				System.out.println("\t3) Add/Update/Delete/Read Tickets and Passengers");
				System.out.println("\t4) Add/Update/Delete/Read Airports");
				System.out.println("\t5) Add/Update/Delete/Read Travelers");
				System.out.println("\t6) Add/Update/Delete/Read Employees");
				System.out.println("\t7) Over-ride Trip Cancellation for a ticket. ");
				System.out.println("\t8) Quit ");

				while (true) {
					try {
						input = scan.nextLine();
						selection = Integer.parseInt(input);
						if (selection > 8 || selection < 1) {
							throw new Exception();
						}
						break;
					} catch (Exception e) {
						System.out.println("Please enter a number from 1-8");
					}
				}

				switch (selection) {
				case 1:
					System.out.println("Flights");
					adminFlight();
					break;
				case 2:
					System.out.println("Seats");
					adminSeat();
					break;
				case 3:
					System.out.println("Tickets and Passengers");
					adminTicket();
					break;
				case 4:
					System.out.println("Airports");
					adminAirports();
					break;
				case 5:
					System.out.println("Travelers");
					adminTraveler(2);
					break;
				case 6:
					System.out.println("Employees");
					adminTraveler(3);
					break;
				case 7:
					System.out.println("Cancel");
					adminCancel();
					break;
				case 8:
					System.out.println("Quit");
					main(args);
					return;
				}

				break;

			case 2: // Traveler
				selection = 0;
				System.out.println("\nPlease make a choice: ");
				System.out.println("\t1) Book a Ticket");
				System.out.println("\t2) Cancel an Upcoming Trip");
				System.out.println("\t3) Quit to Previous");

				while (true) {
					try {
						input = scan.nextLine();
						selection = Integer.parseInt(input);
						if (selection > 3 || selection < 1) {
							throw new Exception();
						}
						break;
					} catch (Exception e) {
						System.out.println("Please enter a number from 1-3");
					}
				}

				switch (selection) {
				case 1:
					System.out.println("Book Ticket");
					travelBook(user);
					break;
				case 2:
					System.out.println("Cancel Trip");
					travelCancel(user);
					break;
				case 3:
					System.out.println("Quit");
					selection = -1;
					userRole = -1;
					main(args);
					return;
				}
				break;

			case 3:// Agent
				break;
			}

		}

	}

	private static int addUpdateDeleteRead() {
		String input = "";
		Scanner scan = new Scanner(System.in);
		int selection = 0;

		while (true) {
			try {
				System.out.println("1)Add \n2)Update\n3)Delete\n4)Read");
				input = scan.nextLine();
				selection = Integer.parseInt(input);
				if (selection > 4 || selection < 1) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.out.println("Please enter a number from 1-4");
			}
		}
		return selection;
	}

	// --------------------------------------------------------------------------TRAVELER----------------------------------------------------------------------------------//
	private static void travelBook(User user) throws SQLException {
		System.out.println("Pick the Flight you want to book a ticket for: ");
		AdminService service = new AdminService();
		List<Flight> flist = service.readFlight();
		int count = 0;
		Route route;
		Airport origin, dest;
		String input = "";
		int id = 0;

		for (Flight flight : flist) {
			route = flight.getRoute_id();
		System.out.println(count + 1 + ") " + route.getOriginAirport().getAirportCode() + ", " + route.getOriginAirport().getCity() + " -> "
					+ route.getDestinationAirport().getAirportCode() + ", " + route.getDestinationAirport().getCity());
			count++;
		}
		System.out.println(count + 1 + ") Quit to Previous");
		Scanner scan = new Scanner(System.in);
		while (true) {// Gets the Flight
			try {
				input = scan.nextLine();
				id = Integer.parseInt(input);

				if (id > count + 1 || id <= 0)
					throw new Exception();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid route id");
			}
		}
		if (id == count + 1)
			return;
		Flight flight = flist.get(id - 1);
		while(true)
		{
			System.out.println("Pick the Seat you want to book a ticket for\n");
			System.out.println("1) View Flight Details\n2)First\n3)Business\n4)Economy\n5)Quit to cancel operation");
			try {
				input = scan.nextLine();
				int choice = Integer.parseInt(input);
				if(choice == 5)
					return;
				if(choice == 1)
					System.out.println(flight);
				if(choice <  5 && choice > 1)
					break;
				if (choice >  5 || choice <= 0)
					throw new Exception();
				} catch (Exception e) {
				System.out.println("Please enter a valid route id");
			}
		}
		
		
		// Creates new booking
		String conf = "Conf" + (int) (Math.random() * 9999 + 1);
		Booking booking = new Booking();
		booking.setIs_active(1);
		booking.setConfirmation_code(conf);

		service.addBooking(booking);
		booking.setId(service.readBooking(conf).get(0).getId());

		Flight_Bookings fb = new Flight_Bookings();
		flight.setReserved_seats(flight.getReserved_seats() + 1);
		service.updateFlight(flight);
		fb.setFlight_id(flight);
		fb.setBooking_id(booking);

		service.bookTicket(fb, booking, user);

	}

	private static void travelCancel(User user) throws SQLException {// --------------------------------CANCEL
		System.out.println("Pick the Flight you want to book a ticket for: ");
		AdminService service = new AdminService();
		List<Booking_User> blist = service.readBooking_User(user.getId());
		
		String input = "";
		int count = 1;
		int id = 0;
		List<Flight_Bookings> flight;
		List<Booking_User> flight2 = new ArrayList<>();
		
		for(Booking_User bu:blist)
		{
			flight = service.readFlightBooking(bu.getBooking_id().getId());
			if(flight.size() > 0)
			{
				System.out.println(count+") "+flight.get(0));
				flight2.add(bu);
				count++;
			}
		}
	
		
		System.out.println(count+") Quit");
		System.out.println("Which Trip would you like to cancel");
		Scanner scan = new Scanner(System.in);
		while (true) {// Gets the trip to remove
			try {
				input = scan.nextLine();
				id = Integer.parseInt(input);
				if(id == count)
					return;
				if (id > count || id <= 0)
					throw new Exception();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid route id");
			}
		}
		
		
		Booking_User bu = flight2.get(id-1);
		System.out.println("DELETING "+bu);
		Booking booking = bu.getBooking_id();
		booking.setIs_active(0);
		service.updateBooking(booking);

		List<Booking_Payment> plist = service.readBookingPayment(booking.getId());
		if (plist.size() > 0) {
			Booking_Payment bp = plist.get(0);
			bp.setRefunded(1);
			service.updateBookingPayment(bp);
		}
		service.deleteBooking_User(bu);
	}

	// ----------------------------------------------------------------------------ADMIN-----------------------------------------------------------------------------------//
	// -------------------------------AIRPORTS
	private static void adminAirports() throws SQLException {

		String input = "";
		Scanner scan = new Scanner(System.in);
		int selection = addUpdateDeleteRead();
		AdminService service = new AdminService();
		Airport airport = new Airport();
		List<Airport> list;

		switch (selection) {

		case 1:
			System.out.println("Add");// --------------------------add

			String id = "";
			String city = "";
			System.out.println("Please enter the iata_id");
			while (true) {
				try {
					id = scan.nextLine();
					if (id.length() != 3) {
						throw new Exception();
					}
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			System.out.println("Please enter the city");
			city = scan.nextLine();
			airport = new Airport();
			airport.setAirportCode(id);
			airport.setCity(city);
			list = service.readAirports(id);
			if (list.size() < 1)
				service.addAirport(airport);
			else
				System.out.println("An airport with that id already exists");
			break;

		case 2:
			System.out.println("Update");// ----------------------update
			printList(service.readAirports());
			System.out.println("Please enter the iata_id you want Updated");
			id = "";
			city = "";
			while (true) {
				try {
					id = scan.nextLine();
					if (id.length() != 3) {
						throw new Exception();
					}
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			System.out.println("Please enter the city");
			city = scan.nextLine();
			airport = new Airport();
			airport.setAirportCode(id);
			airport.setCity(city);
			service.updateAirport(airport);
			break;

		case 3:
			System.out.println("Delete");// ----------------DELETE
			printList(service.readAirports());
			System.out.println("Please enter the iata_id you want deleted");
			while (true) {
				try {
					id = scan.nextLine();
					if (id.length() != 3) {
						throw new Exception();
					}
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			list = service.readAirports(id);
			if (list.size() > 0)
				service.deleteAirport(list.get(0));
			break;

		case 4:
			System.out.println("Read"); // ---------------READ

			while (true)// gets user input
			{
				try {
					System.out.println(
							"Type 'readall' to read all, otherwise type the iata_id of the airport you would like read");
					input = scan.nextLine();
					if (input.length() != 3 && !"readall".equalsIgnoreCase(input)) {
						throw new Exception();
					}
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			if (!"readall".equalsIgnoreCase(input))// read by id
				list = service.readAirports(input);
			else
				list = service.readAirports();// read all
			printList(list);
			break;
		}
		// scan.close();
	}

	// -------------------------------Traveler
	private static void adminTraveler(int role_id) throws SQLException {

		String input = "";
		Scanner scan = new Scanner(System.in);
		int selection = addUpdateDeleteRead();
		AdminService service = new AdminService();
		User user = new User();
		List<User> list;

		int id = 0;
		String given_name = "";
		String family_name = "";
		String username = "";
		String email = "";
		String password = "";
		String phone = "";

		switch (selection) {

		case 1:
			System.out.println("Add");// --------------------------add

			System.out.println("Please enter the given_name");
			given_name = scan.nextLine();

			System.out.println("Please enter the family_name");
			family_name = scan.nextLine();

			System.out.println("Please enter the username");
			username = scan.nextLine();

			System.out.println("Please enter the email");
			email = scan.nextLine();

			System.out.println("Please enter the password");
			password = scan.nextLine();

			System.out.println("Please enter the phone");
			phone = scan.nextLine();

			user = new User();
			// user.setId(id);
			user.setRole_id(role_id);
			user.setGiven_name(given_name);
			user.setFamily_name(family_name);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);

			service.addUser(user);
			break;

		case 2:
			System.out.println("Update");// ----------------------update
			printUserList(service.readUser(), role_id);
			System.out.println("Please enter the id you want updated");
			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			System.out.println("Please enter the given_name");
			given_name = scan.nextLine();

			System.out.println("Please enter the family_name");
			family_name = scan.nextLine();

			System.out.println("Please enter the username");
			username = scan.nextLine();

			System.out.println("Please enter the email");
			email = scan.nextLine();

			System.out.println("Please enter the password");
			password = scan.nextLine();

			System.out.println("Please enter the phone");
			phone = scan.nextLine();

			user = new User();
			user.setId(id);

			user.setRole_id(role_id);
			user.setGiven_name(given_name);
			user.setFamily_name(family_name);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);

			service.updateUser(user);
			break;

		case 3:
			System.out.println("Delete");// ----------------DELETE
			printUserList(service.readUser(), role_id);
			System.out.println("Please enter the id you want deleted");
			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			list = service.readUser(id);
			if (list.size() > 0)
				service.deleteUser(list.get(0));

			break;

		case 4:
			System.out.println("Read"); // ---------------READ

			while (true)// gets user input
			{
				try {
					System.out.println("Type '0' to read all, otherwise type the id of the user you would like read");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			if (id != 0)// read by id
				list = service.readUser(id);
			else
				list = service.readUser();// read all
			printUserList(list, role_id);
			break;
		}
		// scan.close();
	}

	// -----------------------------------------------------------------------------Ticket
	private static void adminTicket() throws SQLException {

		String input = "";
		Scanner scan = new Scanner(System.in);
		int selection = addUpdateDeleteRead();
		AdminService service = new AdminService();
		Passenger passenger = new Passenger();
		List<Passenger> list;

		int id = 0;
		Booking booking_id = null;
		String given_name = "";
		String family_name = "";
		String dob = "";
		String gender = "";
		String address = "";

		switch (selection) {

		case 1:
			System.out.println("Add");// --------------------------add

			while (true)// gets user input
			{
				printList(service.readBooking());
				try {
					System.out.println("Enter a booking id or create a new one");
					input = scan.nextLine();
					booking_id = new Booking();
					booking_id.setIs_active(1);
					booking_id.setId(Integer.parseInt(input));

					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			System.out.println("Please enter the given_name");
			given_name = scan.nextLine();

			System.out.println("Please enter the family_name");
			family_name = scan.nextLine();

			System.out.println("Please enter the date of birth YYY-MM-DD");
			dob = scan.nextLine();

			System.out.println("Please enter the gender");
			gender = scan.nextLine();

			System.out.println("Please enter the address");
			address = scan.nextLine();

			passenger = new Passenger();
			passenger.setBooking_id(booking_id);
			passenger.setGiven_name(given_name);
			passenger.setFamily_name(family_name);
			passenger.setDate(dob);
			passenger.setAddress(address);
			passenger.setGender(gender);

			service.addPassenger(passenger);
			break;

		case 2:
			System.out.println("Update");// ----------------------update
			printList(service.readPassenger());
			System.out.println("Please enter the id you want updated");
			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			printList(service.readBooking());
			while (true) {
				try {
					System.out.println("Enter a booking id or create a new one");
					input = scan.nextLine();
					booking_id = new Booking();
					booking_id.setId(Integer.parseInt(input));

					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			System.out.println("Please enter the given_name");
			given_name = scan.nextLine();

			System.out.println("Please enter the family_name");
			family_name = scan.nextLine();

			System.out.println("Please enter the date of birth YYYY-MM-DD");
			dob = scan.nextLine();

			System.out.println("Please enter the gender");
			gender = scan.nextLine();

			System.out.println("Please enter the address");
			address = scan.nextLine();

			passenger = new Passenger();
			passenger.setId(id);
			passenger.setBooking_id(booking_id);
			passenger.setGiven_name(given_name);
			passenger.setFamily_name(family_name);
			passenger.setDate(dob);
			passenger.setAddress(address);
			passenger.setGender(gender);

			service.updatePassenger(passenger);
			break;

		case 3:
			System.out.println("Delete");// ----------------DELETE
			printList(service.readPassenger());
			System.out.println("Please enter the id you want deleted");
			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			list = service.readPassenger(id);
			if (list.size() > 0)
				service.deletePassenger(list.get(0));

			break;

		case 4:
			System.out.println("Read"); // ---------------READ

			while (true)// gets user input
			{
				try {
					System.out.println(
							"Type '0' to read all, otherwise type the id of the passenger you would like read");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			if (id != 0)// read by id
				list = service.readPassenger(id);
			else
				list = service.readPassenger();// read all
			printList(list);
			break;
		}
		// scan.close();
	}

	// -------------------------------Flight
	private static void adminFlight() throws SQLException {

		String input = "";
		Scanner scan = new Scanner(System.in);
		int selection = addUpdateDeleteRead();
		AdminService service = new AdminService();
		Flight flight = new Flight();
		List<Flight> list = null;
		List<Route> routeList = null;
		List<Airplane> airplaneList = null;
		String origin_id = "";
		String dest_id = "";
		Route route = null;
		Airport airport = null;
		Airport airport2 = null;

		int id = 0;
		int existing = -1;
		Route route_id = null;
		Airplane airplane_id = null;
		String departure_time = "";
		int reserved_seats = 0;
		double seat_price = 0.0;

		switch (selection) {

		case 1:
			System.out.println("Add");// --------------------------add
			System.out.println("Please enter the id");
			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			System.out.println("Type '0' for a pre-existing route or '1' for a new route");
			while (true)// gets user input
			{
				try {
					input = scan.nextLine();
					existing = Integer.parseInt(input);
					if (existing != 0 && existing != 1)
						throw new Exception();
					break;
				} catch (Exception e) {
					System.out.println("Please enter 0 or 1");
				}
			}
			if (existing == 0)// pre-existing
			{
				printList(service.readRoutes());
				System.out.println("Please enter the id of the route");

				while (true) {
					try {
						input = scan.nextLine();
						int rid = Integer.parseInt(input);
						routeList = service.readRoutes(rid);
						if (routeList == null || routeList.size() == 0)
							throw new Exception();
						route_id = routeList.get(0);
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid route id");
					}
				}
			} else {// new route
				printList(service.readAirports());
				System.out.println("Please enter an existing airport for the origin id for enter a new one");// origin
																												// id
				while (true) {
					try {
						origin_id = scan.nextLine();
						if (origin_id.length() != 3) {
							throw new Exception();
						}
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid id");
					}
				}
				System.out.println("Please enter an existing airport for the destination id for enter a new one");// dest
																													// id
				while (true) {
					try {
						dest_id = scan.nextLine();
						if (dest_id.length() != 3) {
							throw new Exception();
						}
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid id");
					}
				}
				route_id = new Route();
				airport = new Airport();
				airport.setAirportCode(origin_id);
				route_id.setOriginAirport(airport);
				airport2 = new Airport();
				airport2.setAirportCode(dest_id);
				route_id.setDestinationAirport(airport2);
				service.addRoute(route_id);
				routeList = service.readRoutes(origin_id, dest_id);
				if (routeList != null && routeList.size() > 0)
					route_id = routeList.get(0);
			}
			printList(service.readAirplane());
			System.out.println("Please enter the id of the airplane");// Gets airplane
			while (true) {
				try {
					input = scan.nextLine();
					int airid = Integer.parseInt(input);
					airplaneList = service.readAirplane(airid);
					if (airplaneList == null || airplaneList.size() == 0)
						throw new Exception();
					airplane_id = airplaneList.get(0);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid airplane id");
				}
			}

			System.out.println("Please enter the departure time 'YYYY-MM-DD HH:MM'");// date
			departure_time = scan.nextLine();

			System.out.println("Please enter the number of reserved_seats");
			while (true)// reserved seats
			{
				try {
					input = scan.nextLine();
					reserved_seats = Integer.parseInt(input);

					if (reserved_seats < 0)
						throw new Exception();
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid number");
				}
			}
			System.out.println("Please enter the seat price");
			while (true)// seat price
			{
				try {
					input = scan.nextLine();
					seat_price = Double.parseDouble(input);

					if (seat_price < 0)
						throw new Exception();
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid double");
				}
			}

			flight = new Flight();
			flight.setId(id);
			flight.setAirplane_id(airplane_id);
			flight.setDeparture_time(departure_time);
			flight.setReserved_seats(reserved_seats);
			flight.setRoute_id(route_id);
			flight.setSeat_price((float) seat_price);
			service.addFlight(flight);
			break;

		case 2:
			System.out.println("Update");// --------------------------update
			printList(service.readFlight());
			System.out.println("Please enter the id you want updated");
			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			System.out.println("Type '0' for a pre-existing route or '1' for a new route");
			while (true)// gets user input
			{
				try {
					input = scan.nextLine();
					existing = Integer.parseInt(input);
					if (existing != 0 && existing != 1)
						throw new Exception();
					break;
				} catch (Exception e) {
					System.out.println("Please enter 0 or 1");
				}
			}
			if (existing == 0)// pre-existing
			{
				printList(service.readRoutes());
				System.out.println("Please enter the id of the route");

				while (true) {
					try {
						input = scan.nextLine();
						int rid = Integer.parseInt(input);
						routeList = service.readRoutes(rid);
						if (routeList == null || routeList.size() == 0)
							throw new Exception();
						route_id = routeList.get(0);
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid route id");
					}
				}
			} else {// new route
				printList(service.readAirports());
				System.out.println("Please enter an existing airport for the origin id for enter a new one");// origin
																												// id
				while (true) {
					try {
						origin_id = scan.nextLine();
						if (origin_id.length() != 3) {
							throw new Exception();
						}
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid id");
					}
				}
				System.out.println("Please enter an existing airport for the destination id for enter a new one");// dest
																													// id
				while (true) {
					try {
						dest_id = scan.nextLine();
						if (dest_id.length() != 3) {
							throw new Exception();
						}
						break;
					} catch (Exception e) {
						System.out.println("Please enter a valid id");
					}
				}
				route_id = new Route();
				airport = new Airport();
				airport.setAirportCode(origin_id);
				route_id.setOriginAirport(airport);
				airport2 = new Airport();
				airport2.setAirportCode(dest_id);
				route_id.setDestinationAirport(airport2);
				service.addRoute(route_id);
				routeList = service.readRoutes(origin_id, dest_id);
				if (routeList != null && routeList.size() > 0)
					route_id = routeList.get(0);
			}
			printList(service.readAirplane());
			System.out.println("Please enter the id of the airplane");// Gets airplane
			while (true) {
				try {
					input = scan.nextLine();
					int airid = Integer.parseInt(input);
					airplaneList = service.readAirplane(airid);
					if (airplaneList == null || airplaneList.size() == 0)
						throw new Exception();
					airplane_id = airplaneList.get(0);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid airplane id");
				}
			}

			System.out.println("Please enter the departure time 'YYYY-MM-DD HH:MM'");// date
			departure_time = scan.nextLine();

			System.out.println("Please enter the number of reserved_seats");
			while (true)// reserved seats
			{
				try {
					input = scan.nextLine();
					reserved_seats = Integer.parseInt(input);

					if (reserved_seats < 0)
						throw new Exception();
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid number");
				}
			}
			System.out.println("Please enter the seat price");
			while (true)// seat price
			{
				try {
					input = scan.nextLine();
					seat_price = Double.parseDouble(input);

					if (seat_price < 0)
						throw new Exception();
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid double");
				}
			}

			flight = new Flight();
			flight.setId(id);
			flight.setAirplane_id(airplane_id);
			flight.setDeparture_time(departure_time);
			flight.setReserved_seats(reserved_seats);
			flight.setRoute_id(route_id);
			flight.setSeat_price((float) seat_price);
			service.updateFlight(flight);
			break;

		case 3:
			System.out.println("Delete");// ----------------DELETE
			printList(service.readFlight());
			System.out.println("Please enter the id you want deleted");

			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			list = service.readFlight(id);
			if (list.size() > 0)
				service.deleteFlight(list.get(0));

			break;

		case 4:
			System.out.println("Read"); // ---------------READ

			while (true)// gets user input
			{
				try {
					System.out.println("Type '0' to read all, otherwise type the id of the Flight you would like read");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			if (id != 0)// read by id
				list = service.readFlight(id);
			else
				list = service.readFlight();// read all
			printFlight(list);
			break;
		}
		// scan.close();
	}

	// -----------------------------------------------------SEAT
	private static void adminSeat() throws SQLException {

		String input = "";
		Scanner scan = new Scanner(System.in);
		int selection = addUpdateDeleteRead();
		AdminService service = new AdminService();
		List<Flight> list;
		List<Booking> bookingList;
		List<Flight_Bookings> fbList;

		int id = 0;
		Booking booking_id = null;
		Flight flight_id = null;
		Flight_Bookings flight_booking = new Flight_Bookings();

		switch (selection) {

		case 1:
			System.out.println("Add");// --------------------------add

			printList(service.readBooking());
			while (true)// gets user booking id
			{
				try {
					System.out.println("Enter the booking id");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					bookingList = service.readBooking(id);
					if (bookingList == null || bookingList.size() < 1)
						throw new Exception();
					booking_id = bookingList.get(0);
					booking_id.setIs_active(1);//makes booking active
					service.updateBooking(booking_id);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			printList(service.readFlight());
			while (true)// gets user flight id
			{
				try {
					System.out.println("Enter the flight id");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					list = service.readFlight(id);
					if (list == null || list.size() < 1)
						throw new Exception();
					flight_id = list.get(0);
					flight_id.setReserved_seats(flight_id.getReserved_seats() + 1);
					service.updateFlight(flight_id);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			flight_booking.setBooking_id(booking_id);
			flight_booking.setFlight_id(flight_id);

			service.addFlightBooking(flight_booking);
			break;

		case 2:
			System.out.println("Update");// ----------------------update
			printList(service.readFlightBooking());
			while (true)// gets user booking id
			{
				try {
					System.out.println("Enter the booking id you want to update");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					fbList = service.readFlightBooking(id);
					if (fbList == null || fbList.size() < 1)
						throw new Exception();
					flight_booking = fbList.get(0);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			list = service.readFlight(flight_booking.getFlight_id().getId());
			Flight flight = list.get(0);
			flight.setReserved_seats(flight.getReserved_seats() - 1);
			service.updateFlight(flight);

			printList(service.readFlight());
			while (true)// gets user flight id
			{
				try {
					System.out.println("Enter the flight id");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					list = service.readFlight(id);
					if (list == null || list.size() < 1)
						throw new Exception();
					flight_id = list.get(0);
					flight_id.setReserved_seats(flight_id.getReserved_seats() + 1);
					service.updateFlight(flight_id);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			flight_booking.setFlight_id(flight_id);

			service.updateFlightBooking(flight_booking);
			break;

		case 3:
			System.out.println("Delete");// ----------------DELETE
			printList(service.readFlightBooking());
			System.out.println("Please enter the booking id you want deleted");
			while (true) {
				try {
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}
			fbList = service.readFlightBooking(id);
			if (fbList.size() > 0)
				service.deleteFlightBooking(fbList.get(0));

			break;

		case 4:
			System.out.println("Read"); // ---------------READ

			while (true)// gets user input
			{
				try {
					System.out.println(
							"Type '0' to read all, otherwise type the id of the booking you would like read the seats of");
					input = scan.nextLine();
					id = Integer.parseInt(input);
					break;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
				}
			}

			if (id != 0)// read by id
				printList(service.readFlightBooking(id));
			else
				printList (service.readFlightBooking());// read all
			
			break;
		}
		// scan.close();
	}
//------------------------------------------------------------------CANCEL
	private static void adminCancel() throws SQLException {
		String input = "";
		Scanner scan = new Scanner(System.in);
		AdminService service = new AdminService();

		List<Booking> list = null;
		List<Booking_Payment> paymentList = null;
		Booking booking;
		Booking_Payment bookingpayment;

		int id = 0;

		printActive(service.readBooking());
		System.out.println("Please enter the id of the booking you want ot cancel");
		while (true) {
			try {
				input = scan.nextLine();
				id = Integer.parseInt(input);
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid id");
			}
		}
		list = service.readBooking(id);
		paymentList = service.readBookingPayment(id);
		if (list != null && list.size() > 0)
			booking = list.get(0);
		else {
			System.out.println("Couldn't find a booking with that id");
			return;
		}
		booking.setIs_active(0);
		service.updateBooking(booking);
		if (paymentList != null && paymentList.size() > 0) {
			bookingpayment = paymentList.get(0);
			bookingpayment.setRefunded(1);
			service.updateBookingPayment(bookingpayment);
		}

	}

	// prints out a list
	private static <T> void printList(List<T> list) {
		for (T x : list) {
			System.out.println(x);
		}
	}
	
	private static void printFlight(List<Flight> list) {
		for(Flight x:list)
		{
			System.out.println(x.print());
		}
			
	}
	private static void printActive(List<Booking> list)
	{
		for(Booking x:list)
		{
			if(x.getIs_active()==1)
				System.out.println(x);
		}
	}

	// prints out a list
	private static void printUserList(List<User> list, int role_id) {
		for (User x : list) {
			if (x.getRole_id() == role_id)
				System.out.println(x);
		}
	}
}

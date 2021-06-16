package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Route;

public class RouteDAO extends BaseDAO<Route> {

	public RouteDAO(Connection conn) {
		super(conn);
	}

	public void addRoute(Route route) throws ClassNotFoundException, SQLException {
		save("insert into route(origin_id,destination_id) VALUES(?, ?)", new Object[] { route.getOriginAirport().getAirportCode(),
				route.getDestinationAirport().getAirportCode() });
	}

	public void updateRoute(Route route) throws ClassNotFoundException, SQLException {
		save("update route set origin_id = ? , destination_id = ? where id = ?",
				new Object[] { route.getOriginAirport().getAirportCode(),
						route.getDestinationAirport().getAirportCode(), route.getId() });
	}

	public void deleteRoute(Route route) throws ClassNotFoundException, SQLException {
		save("delete from route where id = ?", new Object[] { route.getId() });
	}

	public List<Route> readAllRoutes() throws ClassNotFoundException, SQLException {
		return read("select * from route", null);
	}
	
	public List<Route> readRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
		return read("select * from route where destination_id = ? or origin_id = ?", new Object[] {airportCode, airportCode});
	}
	
	public List<Route> readRoutesByAirportCodes(String origin,String dest) throws ClassNotFoundException, SQLException {
		return read("select * from route where destination_id = ? and origin_id = ?", new Object[] {dest, origin});
	}
	
	public List<Route> readRoutesById(int id) throws ClassNotFoundException, SQLException {
		return read("select * from route where id = ?", new Object[] {id});
	}

	public List<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Route> routes = new ArrayList<>();
		while (rs.next()) {
			Route route = new Route();
			route.setId(rs.getInt("id"));
			route.setDestinationAirport(new Airport());
			route.setOriginAirport(new Airport());
			route.getOriginAirport().setAirportCode(rs.getString("origin_id"));
			route.getDestinationAirport().setAirportCode(rs.getString("destination_id"));
			routes.add(route);
		}
		return routes;
	}

}
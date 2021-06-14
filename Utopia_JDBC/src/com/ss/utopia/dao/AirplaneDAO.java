package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Airplane_Type;

public class AirplaneDAO extends BaseDAO<Airplane> {

	public AirplaneDAO(Connection conn) {
		super(conn);
	}

	public void addAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("insert into airplane VALUES(?,?)", new Object[] { airplane.getId(),airplane.getType_id().getId() });
	}

	public void updateAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("update airplane set type_id = ? where id = ?",
				new Object[] {airplane.getType_id().getId(),airplane.getId() });
	}

	public void deleteAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("delete from airplane where id = ?", new Object[] { airplane.getId() });
	}

	public List<Airplane> readAll() throws ClassNotFoundException, SQLException {
		return read("select * from airplane", null);
	}
	
	public List<Airplane> readAirplaneByAirplane_id(int id) throws ClassNotFoundException, SQLException {
		return read("select * from airplane where id = ?", new Object[] {id});
	}

	public List<Airplane> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airplane> airplanes = new ArrayList<>();
		while (rs.next()) {
			Airplane airplane = new Airplane();
			airplane.setId(rs.getInt("id"));
			airplane.setType_id(new Airplane_Type());
			airplane.getType_id().setId(rs.getInt("type_id"));
			airplanes.add(airplane);
		}
		return airplanes;
	}

}
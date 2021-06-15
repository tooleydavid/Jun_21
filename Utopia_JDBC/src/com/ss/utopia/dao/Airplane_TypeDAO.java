package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airplane_Type;

public class Airplane_TypeDAO extends BaseDAO<Airplane_Type> {

	public Airplane_TypeDAO(Connection conn) {
		super(conn);
	}

	public void addAirplane_Type(Airplane_Type airplane_type) throws ClassNotFoundException, SQLException {
		save("insert into airplane_type(id,max_capacity) VALUES(?,?)", new Object[] { airplane_type.getId(),airplane_type.getMax_capacity() });
	}

	public void updateAirplane_Type(Airplane_Type airplane_type) throws ClassNotFoundException, SQLException {
		save("update airplane_type set max_capacity = ? where id = ?",
				new Object[] {airplane_type.getMax_capacity(),airplane_type.getId() });
	}

	public void deleteAirplane_Type(Airplane_Type airplane_type) throws ClassNotFoundException, SQLException {
		save("delete from airplane_type where id = ?", new Object[] { airplane_type.getId() });
	}

	public List<Airplane_Type> readAll() throws ClassNotFoundException, SQLException {
		return read("select * from airplane_type", null);
	}
	
	public List<Airplane_Type> readAirplane_TypeByAirplane_Type_id(int id) throws ClassNotFoundException, SQLException {
		return read("select * from airplane_type where id = ?", new Object[] {id});
	}

	public List<Airplane_Type> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airplane_Type> airplane_types = new ArrayList<>();
		while (rs.next()) {
			Airplane_Type airplane_type = new Airplane_Type();
			airplane_type.setId(rs.getInt("id"));
			airplane_type.setMax_capacity(rs.getInt("max_capacity"));
			airplane_types.add(airplane_type);
		}
		return airplane_types;
	}

}
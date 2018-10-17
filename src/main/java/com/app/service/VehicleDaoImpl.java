package com.app.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.app.model.Vehicle;

@Repository
public class VehicleDaoImpl implements VehicleDao {

	final String SELECT_BY_ID = "SELECT * FROM vehicle WHERE id=:id";
	final String INSERT_QUERY = "insert into vehicle (id, name, type, version, date) values (:id, :name, :type, :version, :date)";
	final String UPDATE_QUERY = "update vehicle set name = :name where id = :id";
	final String DELETE_QUERY = "delete from vehicle where id = :id";

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Vehicle findByName(String name) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);

		String sql = "SELECT * FROM vehicle WHERE name=:name";

		Vehicle result = namedParameterJdbcTemplate.queryForObject(sql, params, new VehicleMapper());

		return result;

	}

	@Override
	public Vehicle getVehicle(int id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		Vehicle vehicle = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, params, new VehicleMapper());
		return vehicle;
	}
	
	@Override
	public int save(Vehicle vehicle) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", vehicle.getId());
		paramMap.put("name", vehicle.getName());
		paramMap.put("type", vehicle.getType());
		paramMap.put("date", vehicle.getDate());
		paramMap.put("version", vehicle.getVersion());
		return namedParameterJdbcTemplate.update(INSERT_QUERY, paramMap);
	}

	@Override
	public void update(Vehicle vehicle) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", vehicle.getId()).addValue("id",
				vehicle.getName());
		int status = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		if (status != 0) {
			System.out.println("Employee data updated for ID " + vehicle.getId());
		} else {
			System.out.println("No Employee found with ID " + vehicle.getId());
		}
	}

	@Override
	public void deleteVehicleById(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		int status = namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
		if (status != 0) {
			System.out.println("Employee data deleted for ID " + id);
		} else {
			System.out.println("No Employee found with ID " + id);
		}
	}

	@Override
	public List<Vehicle> findAll() {

		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM vehicle";

		List<Vehicle> result = namedParameterJdbcTemplate.query(sql, params, new VehicleMapper());

		return result;

	}

	private static final class VehicleMapper implements RowMapper<Vehicle> {

		public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Vehicle Vehicle = new Vehicle();
			Vehicle.setId(rs.getInt("id"));
			Vehicle.setName(rs.getString("name"));
			return Vehicle;
		}
	}

}
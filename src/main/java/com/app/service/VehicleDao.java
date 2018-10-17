package com.app.service;
import java.util.List;
import com.app.model.Vehicle;



public interface VehicleDao {

	Vehicle findByName(String name);
	
	List<Vehicle> findAll();
	
	int save(Vehicle vehicle);
	void update(Vehicle vehicle);
	void deleteVehicleById(int id);
	Vehicle getVehicle(int id);

}
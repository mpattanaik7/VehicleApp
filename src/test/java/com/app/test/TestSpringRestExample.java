package com.app.test;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.app.controller.VehicleRestURIConstants;
import com.app.model.Vehicle;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:9090/SpringRestExample";
	
	public static void main(String args[]){
		
		testGetDummyVehicle();
		System.out.println("*****");
		testCreateVehicle();
		System.out.println("*****");
		testGetVehicle();
		System.out.println("*****");
		testGetAllVehicles();
	}

	private static void testGetAllVehicles() {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> vehicles = restTemplate.getForObject(SERVER_URI+VehicleRestURIConstants.GET_ALL_VEHICLE, List.class);
		System.out.println(vehicles.size());
		for(LinkedHashMap map : vehicles){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateVehicle() {
		RestTemplate restTemplate = new RestTemplate();
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1);vehicle.setName("R1");
		Vehicle response = restTemplate.postForObject(SERVER_URI+VehicleRestURIConstants.CREATE_VEHICLE, vehicle, Vehicle.class);
		printvehicleData(response);
	}

	private static void testGetVehicle() {
		RestTemplate restTemplate = new RestTemplate();
		Vehicle vehicle = restTemplate.getForObject(SERVER_URI+"/rest/vehicle/1", Vehicle.class);
		printvehicleData(vehicle);
	}

	private static void testGetDummyVehicle() {
		RestTemplate restTemplate = new RestTemplate();
		Vehicle vehicle = restTemplate.getForObject(SERVER_URI+VehicleRestURIConstants.DUMMY_VEHICLE, Vehicle.class);
		printvehicleData(vehicle);
	}
	
	public static void printvehicleData(Vehicle vehicle){
		System.out.println("ID="+vehicle.getId()+",Name="+vehicle.getName()+",date="+vehicle.getDate());
	}
}

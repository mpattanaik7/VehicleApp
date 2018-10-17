package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Vehicle;
import com.app.service.VehicleDao;

/**
 * Handles requests for the Vehicle service.
 */
@Controller
public class VehicleController {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired
	private VehicleDao service;
	
	
	@RequestMapping(value = VehicleRestURIConstants.DUMMY_VEHICLE, method = RequestMethod.GET)
	public @ResponseBody List<Vehicle> getDummyVehicle() {
		List<Vehicle> vehicles=service.findAll();
		return vehicles;
	}
	
	@RequestMapping(value = VehicleRestURIConstants.GET_VEHICLE, method = RequestMethod.GET)
	public @ResponseBody Vehicle getVehicle(@PathVariable("id") int vehicleId) {
		logger.info("Start getVehicle. ID="+vehicleId);
		return service.getVehicle(vehicleId);
	}
	
	@RequestMapping(value = VehicleRestURIConstants.GET_ALL_VEHICLE, method = RequestMethod.GET)
	public @ResponseBody List<Vehicle> getAllVehicles() {
		logger.info("Start getAllVehicles.");
		return service.findAll();
	}
	
	@RequestMapping(value = VehicleRestURIConstants.CREATE_VEHICLE, method = RequestMethod.POST)
	public @ResponseBody List<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
		logger.info("Start createVehicle.");
		service.save(vehicle);
		return service.findAll();
	}
	
	@RequestMapping(value = VehicleRestURIConstants.DELETE_VEHICLE, method = RequestMethod.PUT)
	public @ResponseBody List<Vehicle> deleteVehicle(@PathVariable("id") int vehicleId) {
		logger.info("Start deleteVehicle.");
		service.deleteVehicleById(vehicleId);
		return service.findAll();
	}
	
}

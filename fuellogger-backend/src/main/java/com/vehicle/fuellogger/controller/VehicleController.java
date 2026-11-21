package com.vehicle.fuellogger.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.fuellogger.dto.VehicleDto;
import com.vehicle.fuellogger.service.VehicleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	private VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}

	@PostMapping
	public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
		VehicleDto savedVehicle = vehicleService.createVehicle(vehicleDto);
		return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<VehicleDto> getVehicleById(@PathVariable("id") Long vehicleId) {
		VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
		return ResponseEntity.ok(vehicleDto);
	}

	@GetMapping
	public ResponseEntity<List<VehicleDto>> getAllVehicles() {
		List<VehicleDto> vehicles = vehicleService.getAllVehicles();
		return ResponseEntity.ok(vehicles);
	}

	@PutMapping("{id}")
	public ResponseEntity<VehicleDto> updateVehicle(@PathVariable("id") Long vehicleId,
			@RequestBody VehicleDto updatedVehicle) {
		VehicleDto vehicleDto = vehicleService.updateVehicle(vehicleId, updatedVehicle);
		return ResponseEntity.ok(vehicleDto);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long vehicleId) {
		vehicleService.deleteVehicle(vehicleId);
		return ResponseEntity.ok("Vehicle deleted successfully!");
	}
}
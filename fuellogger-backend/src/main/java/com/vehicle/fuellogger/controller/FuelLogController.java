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

import com.vehicle.fuellogger.dto.FuelLogDto;
import com.vehicle.fuellogger.service.FuelLogService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vehicles/{vehicleId}/fuel-logs")
public class FuelLogController {

	private FuelLogService fuelLogService;

	public FuelLogController(FuelLogService fuelLogService) {
		super();
		this.fuelLogService = fuelLogService;
	}

	// Build Add Fuel Log REST API
	@PostMapping
	public ResponseEntity<FuelLogDto> createFuelLog(@PathVariable("vehicleId") Long vehicleId,
			@RequestBody FuelLogDto fuelLogDto) {
		FuelLogDto savedFuelLog = fuelLogService.createFuelLog(vehicleId, fuelLogDto);
		return new ResponseEntity<>(savedFuelLog, HttpStatus.CREATED);
	}

	// Build Get Fuel Log By ID REST API
	@GetMapping("{id}")
	public ResponseEntity<FuelLogDto> getFuelLogById(@PathVariable("id") Long fuelLogId) {
		FuelLogDto fuelLogDto = fuelLogService.getFuelLogById(fuelLogId);
		return ResponseEntity.ok(fuelLogDto);
	}

	// Build Get All Fuel Logs For A Vehicle REST API
	@GetMapping
	public ResponseEntity<List<FuelLogDto>> getFuelLogsByVehicleId(@PathVariable("vehicleId") Long vehicleId) {
		List<FuelLogDto> fuelLogs = fuelLogService.getFuelLogsByVehicleId(vehicleId);
		return ResponseEntity.ok(fuelLogs);
	}

	// Build Update Fuel Log REST API
	@PutMapping("{id}")
	public ResponseEntity<FuelLogDto> updateFuelLog(@PathVariable("id") Long fuelLogId,
			@RequestBody FuelLogDto updatedFuelLog) {
		FuelLogDto fuelLogDto = fuelLogService.updateFuelLog(fuelLogId, updatedFuelLog);
		return ResponseEntity.ok(fuelLogDto);
	}

	// Build Delete Fuel Log REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteFuelLog(@PathVariable("id") Long fuelLogId) {
		fuelLogService.deleteFuelLog(fuelLogId);
		return ResponseEntity.ok("Fuel log deleted successfully!");
	}
}

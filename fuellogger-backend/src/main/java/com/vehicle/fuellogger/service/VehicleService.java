package com.vehicle.fuellogger.service;

import java.util.List;

import com.vehicle.fuellogger.dto.VehicleDto;

public interface VehicleService {
	VehicleDto createVehicle(VehicleDto vehicleDto);

	VehicleDto getVehicleById(Long vehicleId);

	List<VehicleDto> getAllVehicles();

	VehicleDto updateVehicle(Long vehicleId, VehicleDto updatedVehicle);

	void deleteVehicle(Long vehicleId);
}

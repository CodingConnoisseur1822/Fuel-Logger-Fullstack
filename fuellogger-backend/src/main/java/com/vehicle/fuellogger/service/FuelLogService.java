package com.vehicle.fuellogger.service;

import java.util.List;

import com.vehicle.fuellogger.dto.FuelLogDto;

public interface FuelLogService {
	FuelLogDto createFuelLog(Long vehicleId, FuelLogDto fuelLogDto);

	FuelLogDto getFuelLogById(Long fuelLogId);

	List<FuelLogDto> getFuelLogsByVehicleId(Long vehicleId);

	FuelLogDto updateFuelLog(Long fuelLogId, FuelLogDto updatedFuelLog);

	void deleteFuelLog(Long fuelLogId);
}

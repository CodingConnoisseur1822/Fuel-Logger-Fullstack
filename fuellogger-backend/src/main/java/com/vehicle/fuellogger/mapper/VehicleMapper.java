package com.vehicle.fuellogger.mapper;

import com.vehicle.fuellogger.dto.VehicleDto;
import com.vehicle.fuellogger.entity.Vehicle;

public class VehicleMapper {

	public static VehicleDto mapToVehicleDto(Vehicle vehicle) {
		return new VehicleDto(vehicle.getId(), vehicle.getVrm(), vehicle.getMake(), vehicle.getModel(),
				vehicle.getFuelType());
	}

	public static Vehicle mapToVehicle(VehicleDto vehicleDto) {
		return new Vehicle(vehicleDto.getId(), vehicleDto.getVrm(), vehicleDto.getMake(), vehicleDto.getModel(),
				vehicleDto.getFuelType());
	}
}

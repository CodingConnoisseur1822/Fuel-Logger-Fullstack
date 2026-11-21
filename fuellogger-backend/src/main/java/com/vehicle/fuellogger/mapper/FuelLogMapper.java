package com.vehicle.fuellogger.mapper;

import com.vehicle.fuellogger.dto.FuelLogDto;
import com.vehicle.fuellogger.entity.FuelLog;

public class FuelLogMapper {

	public static FuelLogDto mapToFuelLogDto(FuelLog fuelLog) {
		Long vehicleId = null;
		if (fuelLog.getVehicle() != null) {
			vehicleId = fuelLog.getVehicle().getId();
		}

		return new FuelLogDto(fuelLog.getId(), vehicleId, fuelLog.getLogDate(), fuelLog.getOdometer(),
				fuelLog.getLitres(), fuelLog.getTotalCostGbp(), fuelLog.getCalculatedMpg());
	}

	public static FuelLog mapToFuelLog(FuelLogDto fuelLogDto) {
		FuelLog fuelLog = new FuelLog();
		fuelLog.setId(fuelLogDto.getId());
		fuelLog.setLogDate(fuelLogDto.getLogDate());
		fuelLog.setOdometer(fuelLogDto.getOdometer());
		fuelLog.setLitres(fuelLogDto.getLitres());
		fuelLog.setTotalCostGbp(fuelLogDto.getTotalCostGbp());
		fuelLog.setCalculatedMpg(fuelLogDto.getCalculatedMpg());
		return fuelLog;
	}
}

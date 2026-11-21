package com.vehicle.fuellogger.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vehicle.fuellogger.dto.FuelLogDto;
import com.vehicle.fuellogger.entity.FuelLog;
import com.vehicle.fuellogger.entity.Vehicle;
import com.vehicle.fuellogger.exception.ResourceNotFoundException;
import com.vehicle.fuellogger.mapper.FuelLogMapper;
import com.vehicle.fuellogger.repository.FuelLogRepository;
import com.vehicle.fuellogger.repository.VehicleRepository;
import com.vehicle.fuellogger.service.FuelLogService;

@Service
public class FuelLogServiceImpl implements FuelLogService {

	private FuelLogRepository fuelLogRepository;
	private VehicleRepository vehicleRepository;

	public FuelLogServiceImpl(FuelLogRepository fuelLogRepository, VehicleRepository vehicleRepository) {
		super();
		this.fuelLogRepository = fuelLogRepository;
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public FuelLogDto createFuelLog(Long vehicleId, FuelLogDto fuelLogDto) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist with given id: " + vehicleId));

		// Map DTO to Entity and attach the Vehicle relationship
		FuelLog fuelLog = FuelLogMapper.mapToFuelLog(fuelLogDto);
		fuelLog.setVehicle(vehicle);

		// Calculate MPG using our helper
		calculateAndSetMpg(fuelLog, vehicleId, fuelLogDto.getOdometer(), fuelLogDto.getLitres(), null);

		FuelLog savedFuelLog = fuelLogRepository.save(fuelLog);
		return FuelLogMapper.mapToFuelLogDto(savedFuelLog);
	}

	@Override
	public FuelLogDto getFuelLogById(Long fuelLogId) {
		FuelLog fuelLog = fuelLogRepository.findById(fuelLogId).orElseThrow(
				() -> new ResourceNotFoundException("Fuel log does not exist with given id: " + fuelLogId));
		return FuelLogMapper.mapToFuelLogDto(fuelLog);
	}

	@Override
	public List<FuelLogDto> getFuelLogsByVehicleId(Long vehicleId) {
		if (!vehicleRepository.existsById(vehicleId)) {
			throw new ResourceNotFoundException("Vehicle does not exist with given id: " + vehicleId);
		}
		List<FuelLog> fuelLogs = fuelLogRepository.findByVehicleId(vehicleId);
		return fuelLogs.stream().map(FuelLogMapper::mapToFuelLogDto).collect(Collectors.toList());
	}

	@Override
	public FuelLogDto updateFuelLog(Long fuelLogId, FuelLogDto updatedFuelLog) {
		FuelLog fuelLog = fuelLogRepository.findById(fuelLogId).orElseThrow(
				() -> new ResourceNotFoundException("Fuel log does not exist with given id: " + fuelLogId));

		fuelLog.setLogDate(updatedFuelLog.getLogDate());
		fuelLog.setOdometer(updatedFuelLog.getOdometer());
		fuelLog.setLitres(updatedFuelLog.getLitres());
		fuelLog.setTotalCostGbp(updatedFuelLog.getTotalCostGbp());

		// Re-calculate MPG on update
		calculateAndSetMpg(fuelLog, fuelLog.getVehicle().getId(), updatedFuelLog.getOdometer(),
				updatedFuelLog.getLitres(), fuelLogId);

		FuelLog savedFuelLog = fuelLogRepository.save(fuelLog);
		return FuelLogMapper.mapToFuelLogDto(savedFuelLog);
	}

	@Override
	public void deleteFuelLog(Long fuelLogId) {
		FuelLog fuelLog = fuelLogRepository.findById(fuelLogId).orElseThrow(
				() -> new ResourceNotFoundException("Fuel log does not exist with given id: " + fuelLogId));
		fuelLogRepository.deleteById(fuelLogId);
	}

	private void calculateAndSetMpg(FuelLog fuelLog, Long vehicleId, Integer odometer, Double litres,
			Long excludeLogId) {
		List<FuelLog> vehicleLogs = fuelLogRepository.findByVehicleId(vehicleId);

		FuelLog previousLog = null;

		for (FuelLog log : vehicleLogs) {
			if (excludeLogId != null && log.getId().equals(excludeLogId)) {
				continue;
			}

			if (log.getOdometer() < odometer) {
				if (previousLog == null || log.getOdometer() > previousLog.getOdometer()) {
					previousLog = log;
				}
			}
		}

		if (previousLog != null) {
			double milesDriven = odometer - previousLog.getOdometer();
			double litresAdded = litres;

			if (litresAdded > 0 && milesDriven > 0) {
				double imperialGallons = litresAdded / 4.54609;
				double mpg = milesDriven / imperialGallons;
				fuelLog.setCalculatedMpg(Math.round(mpg * 100.0) / 100.0);
				return;
			}
		}

		fuelLog.setCalculatedMpg(null);
	}
}
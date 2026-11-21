package com.vehicle.fuellogger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.fuellogger.entity.FuelLog;

public interface FuelLogRepository extends JpaRepository<FuelLog, Long> {
	List<FuelLog> findByVehicleId(Long vehicleId);

	// Used for sequential MPG lookup (finds the immediately preceding log)
	Optional<FuelLog> findFirstByVehicleIdAndOdometerLessThanOrderByOdometerDesc(Long vehicleId, Integer odometer);
}

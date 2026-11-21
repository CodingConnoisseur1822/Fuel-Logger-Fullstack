package com.vehicle.fuellogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.fuellogger.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

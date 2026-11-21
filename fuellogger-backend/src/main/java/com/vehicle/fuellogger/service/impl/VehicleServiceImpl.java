package com.vehicle.fuellogger.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vehicle.fuellogger.dto.VehicleDto;
import com.vehicle.fuellogger.entity.Vehicle;
import com.vehicle.fuellogger.exception.ResourceNotFoundException;
import com.vehicle.fuellogger.mapper.VehicleMapper;
import com.vehicle.fuellogger.repository.VehicleRepository;
import com.vehicle.fuellogger.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	private VehicleRepository vehicleRepository;

	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public VehicleDto createVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = VehicleMapper.mapToVehicle(vehicleDto);
		Vehicle savedVehicle = vehicleRepository.save(vehicle);
		return VehicleMapper.mapToVehicleDto(savedVehicle);
	}

	@Override
	public VehicleDto getVehicleById(Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist with given id: " + vehicleId));
		return VehicleMapper.mapToVehicleDto(vehicle);
	}

	@Override
	public List<VehicleDto> getAllVehicles() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		return vehicles.stream().map(VehicleMapper::mapToVehicleDto).collect(Collectors.toList());
	}

	@Override
	public VehicleDto updateVehicle(Long vehicleId, VehicleDto updatedVehicle) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist with given id: " + vehicleId));

		vehicle.setVrm(updatedVehicle.getVrm());
		vehicle.setMake(updatedVehicle.getMake());
		vehicle.setModel(updatedVehicle.getModel());
		vehicle.setFuelType(updatedVehicle.getFuelType());

		Vehicle updatedVehicleObj = vehicleRepository.save(vehicle);
		return VehicleMapper.mapToVehicleDto(updatedVehicleObj);
	}

	@Override
	public void deleteVehicle(Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist with given id: " + vehicleId));
		vehicleRepository.deleteById(vehicleId);
	}
}

package com.vehicle.fuellogger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "vrm", nullable = false, unique = true)
	private String vrm; // e.g., AB12CDE

	@Column(name = "make", nullable = false)
	private String make;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "fuel_type", nullable = false)
	private String fuelType; // e.g., PETROL, DIESEL

	public Vehicle() {
	}

	public Vehicle(Long id, String vrm, String make, String model, String fuelType) {
		this.id = id;
		this.vrm = vrm;
		this.make = make;
		this.model = model;
		this.fuelType = fuelType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVrm() {
		return vrm;
	}

	public void setVrm(String vrm) {
		this.vrm = vrm;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
}

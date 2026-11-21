package com.vehicle.fuellogger.dto;

public class VehicleDto {
	private Long id;
	private String vrm;
	private String make;
	private String model;
	private String fuelType;

	public VehicleDto() {
	}

	public VehicleDto(Long id, String vrm, String make, String model, String fuelType) {
		super();
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

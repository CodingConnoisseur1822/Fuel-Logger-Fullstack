package com.vehicle.fuellogger.dto;

import java.time.LocalDate;

public class FuelLogDto {
	private Long id;
	private Long vehicleId;
	private LocalDate logDate;
	private Integer odometer;
	private Double litres;
	private Double totalCostGbp;
	private Double calculatedMpg;

	public FuelLogDto() {
	}

	public FuelLogDto(Long id, Long vehicleId, LocalDate logDate, Integer odometer, Double litres, Double totalCostGbp,
			Double calculatedMpg) {
		super();
		this.id = id;
		this.vehicleId = vehicleId;
		this.logDate = logDate;
		this.odometer = odometer;
		this.litres = litres;
		this.totalCostGbp = totalCostGbp;
		this.calculatedMpg = calculatedMpg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public LocalDate getLogDate() {
		return logDate;
	}

	public void setLogDate(LocalDate logDate) {
		this.logDate = logDate;
	}

	public Integer getOdometer() {
		return odometer;
	}

	public void setOdometer(Integer odometer) {
		this.odometer = odometer;
	}

	public Double getLitres() {
		return litres;
	}

	public void setLitres(Double litres) {
		this.litres = litres;
	}

	public Double getTotalCostGbp() {
		return totalCostGbp;
	}

	public void setTotalCostGbp(Double totalCostGbp) {
		this.totalCostGbp = totalCostGbp;
	}

	public Double getCalculatedMpg() {
		return calculatedMpg;
	}

	public void setCalculatedMpg(Double calculatedMpg) {
		this.calculatedMpg = calculatedMpg;
	}
}

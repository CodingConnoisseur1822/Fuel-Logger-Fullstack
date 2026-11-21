package com.vehicle.fuellogger.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fuel_logs")
public class FuelLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id", nullable = false)
	private Vehicle vehicle;

	@Column(name = "log_date", nullable = false)
	private LocalDate logDate;

	@Column(name = "odometer", nullable = false)
	private Integer odometer;

	@Column(name = "litres", nullable = false)
	private Double litres;

	@Column(name = "total_cost_gbp", nullable = false)
	private Double totalCostGbp;

	@Column(name = "calculated_mpg")
	private Double calculatedMpg;

	public FuelLog() {
	}

	public FuelLog(Long id, Vehicle vehicle, LocalDate logDate, Integer odometer, Double litres, Double totalCostGbp, Double calculatedMpg) {
		this.id = id;
		this.vehicle = vehicle;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

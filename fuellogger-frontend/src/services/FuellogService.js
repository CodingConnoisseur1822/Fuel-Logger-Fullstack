import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/vehicles';

export function listFuelLogs(vehicleId) {
    return axios.get(`${REST_API_BASE_URL}/${vehicleId}/fuel-logs`);
}

export function createFuelLog(vehicleId, fuelLog) {
    return axios.post(`${REST_API_BASE_URL}/${vehicleId}/fuel-logs`, fuelLog);
}

export function getFuelLog(vehicleId, fuelLogId) {
    return axios.get(`${REST_API_BASE_URL}/${vehicleId}/fuel-logs/${fuelLogId}`);
}

export function updateFuelLog(vehicleId, fuelLogId, fuelLog) {
    return axios.put(`${REST_API_BASE_URL}/${vehicleId}/fuel-logs/${fuelLogId}`, fuelLog);
}

export function deleteFuelLog(vehicleId, fuelLogId) {
    return axios.delete(`${REST_API_BASE_URL}/${vehicleId}/fuel-logs/${fuelLogId}`);
}
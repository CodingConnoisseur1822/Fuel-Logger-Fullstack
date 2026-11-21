import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/vehicles';

export function listVehicles() {
    return axios.get(REST_API_BASE_URL);
}

export function createVehicle(vehicle) {
    return axios.post(REST_API_BASE_URL, vehicle);
}

export function getVehicle(vehicleId) {
    return axios.get(REST_API_BASE_URL + '/' + vehicleId);
}

export function updateVehicle(vehicleId, vehicle) {
    return axios.put(REST_API_BASE_URL + '/' + vehicleId, vehicle);
}

export function deleteVehicle(vehicleId) {
    return axios.delete(REST_API_BASE_URL + '/' + vehicleId);
}
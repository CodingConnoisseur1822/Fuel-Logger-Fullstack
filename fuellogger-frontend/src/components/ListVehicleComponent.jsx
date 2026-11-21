import React, { useEffect, useState } from 'react'
import { listVehicles, deleteVehicle } from '../services/VehicleService'
import { useNavigate } from 'react-router-dom'

export function ListVehicleComponent() {
  const [vehicles, setVehicles] = useState([])
  const navigator = useNavigate();

  useEffect(() => {
    getAllVehicles();
  }, [])

  function getAllVehicles() {
    listVehicles().then((response) => {
        setVehicles(response.data);
    }).catch(error => {
        console.error(error);
    })
  }

  function addNewVehicle() {
    navigator('/add-vehicle');
  }

  function removeVehicle(id) {
    deleteVehicle(id).then(() => {
        getAllVehicles();
    }).catch(error => {
        console.error(error);
    })
  }

  function updateVehicle(id) {
    navigator(`/edit-vehicle/${id}`);
  }

  function viewFuelLogs(id) {
    navigator(`/vehicles/${id}/fuel-logs`);
  }

  return (
    <div className='container'>
        <h2 className='text-center mb-4'>Vehicle Fleet</h2>
        <button className='btn btn-primary mb-4' onClick={addNewVehicle}>Add Vehicle</button>
        
        <div className='row'>
            {
                vehicles.map(vehicle =>
                    <div className='col-md-4 mb-4' key={vehicle.id}>
                        <div className='card h-100 shadow-sm border-0'>
                            <div className='card-body d-flex flex-column'>
                                <div className='d-flex justify-content-between align-items-center mb-2'>
                                    <h4 className='card-title text-primary mb-0 font-monospace'>{vehicle.vrm}</h4>
                                    <span className='badge bg-secondary'>{vehicle.fuelType}</span>
                                </div>
                                <h6 className='card-subtitle text-muted mb-3'>{vehicle.make} {vehicle.model}</h6>
                                <hr />
                                <div className='mt-auto'>
                                    <button className='btn btn-outline-primary btn-sm w-100 mb-2' onClick={() => viewFuelLogs(vehicle.id)}>
                                        View Fuel Logs
                                    </button>
                                    <div className='d-flex justify-content-between'>
                                        <button className='btn btn-info btn-sm text-white px-3' onClick={() => updateVehicle(vehicle.id)}>Edit</button>
                                        <button className='btn btn-danger btn-sm px-3' onClick={() => removeVehicle(vehicle.id)}>Delete</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                )
            }
            {vehicles.length === 0 && (
                <div className='col-12 text-center text-muted'>
                    <p>No vehicles found. Click "Add Vehicle" to register one.</p>
                </div>
            )}
        </div>
    </div>
  )
}

export default ListVehicleComponent
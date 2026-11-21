import React, { useEffect, useState } from 'react'
import { listFuelLogs, deleteFuelLog } from '../services/FuellogService'
import { useParams, useNavigate } from 'react-router-dom'

function ListFuelLogComponent() {
  const [fuelLogs, setFuelLogs] = useState([]);
  const { vehicleId } = useParams();
  const navigator = useNavigate();

  useEffect(() => {
    getLogs();
  }, [vehicleId]);

  function getLogs() {
    listFuelLogs(vehicleId).then(response => {
        setFuelLogs(response.data);
    }).catch(error => {
        console.error(error);
    });
  }

  function addFuelLog() {
    navigator(`/vehicles/${vehicleId}/add-fuel-log`);
  }

  function removeLog(id) {
    deleteFuelLog(vehicleId, id).then(() => {
        getLogs();
    }).catch(error => {
        console.error(error);
    });
  }

  return (
    <div className='container'>
        <div className='d-flex justify-content-between align-items-center mb-4'>
            <h2>Fuel Log History</h2>
            <div>
                <button className='btn btn-secondary me-2' onClick={() => navigator('/vehicles')}>Back to Vehicles</button>
                <button className='btn btn-primary' onClick={addFuelLog}>Add Fuel Log</button>
            </div>
        </div>

        <div className='row'>
            {
                fuelLogs.map(log =>
                    <div className='col-md-4 mb-3' key={log.id}>
                        <div className='card h-100 shadow-sm'>
                            <div className='card-body'>
                                <h5 className='card-title text-success'>Date: {log.logDate}</h5>
                                <hr />
                                <p className='mb-1'><strong>Odometer:</strong> {log.odometer} mi/km</p>
                                <p className='mb-1'><strong>Litres:</strong> {log.litres} L</p>
                                <p className='mb-1'><strong>Total Cost:</strong> £{log.totalCostGbp}</p>
                                <p className='mb-3'><strong>Calculated MPG:</strong> {log.calculatedMpg ? log.calculatedMpg : 'N/A'}</p>
                                
                                <button className='btn btn-danger btn-sm w-100' onClick={() => removeLog(log.id)}>Delete Log</button>
                            </div>
                        </div>
                    </div>
                )
            }
            {fuelLogs.length === 0 && (
                <div className='col-12 text-center text-muted'>
                    <p>No fuel logs recorded for this vehicle yet.</p>
                </div>
            )}
        </div>
    </div>
  )
}

export default ListFuelLogComponent
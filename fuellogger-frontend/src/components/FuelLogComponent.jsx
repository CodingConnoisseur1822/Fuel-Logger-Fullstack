import React, { useState } from 'react'
import { createFuelLog } from '../services/FuellogService'
import { useNavigate, useParams } from 'react-router-dom';

export function FuelLogComponent() {
    const [logDate, setLogDate] = useState('');
    const [odometer, setOdometer] = useState('');
    const [litres, setLitres] = useState('');
    const [totalCostGbp, setTotalCostGbp] = useState('');

    const { vehicleId } = useParams();
    const navigator = useNavigate();

    function saveFuelLog(e) {
        e.preventDefault();
        const fuelLogDto = { logDate, odometer: parseInt(odometer), litres: parseFloat(litres), totalCostGbp: parseFloat(totalCostGbp) };

        createFuelLog(vehicleId, fuelLogDto).then(() => {
            navigator(`/vehicles/${vehicleId}/fuel-logs`);
        }).catch(error => {
            console.error(error);
        });
    }

    return (
        <div className='container mt-4'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 shadow'>
                    <h2 className='text-center mt-3'>Add Fuel Log</h2>
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Log Date:</label>
                                <input
                                    type='date'
                                    value={logDate}
                                    className='form-control'
                                    onChange={(e) => setLogDate(e.target.value)}
                                />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Odometer:</label>
                                <input
                                    type='number'
                                    placeholder='Enter Odometer'
                                    value={odometer}
                                    className='form-control'
                                    onChange={(e) => setOdometer(e.target.value)}
                                />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Litres:</label>
                                <input
                                    type='number'
                                    step='0.01'
                                    placeholder='Enter Litres'
                                    value={litres}
                                    className='form-control'
                                    onChange={(e) => setLitres(e.target.value)}
                                />
                            </div>

                            <div className='form-group mb-3'>
                                <label className='form-label'>Total Cost (£):</label>
                                <input
                                    type='number'
                                    step='0.01'
                                    placeholder='Enter Cost in GBP'
                                    value={totalCostGbp}
                                    className='form-control'
                                    onChange={(e) => setTotalCostGbp(e.target.value)}
                                />
                            </div>

                            <button className='btn btn-success w-100' onClick={saveFuelLog}>Save Fuel Log</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FuelLogComponent
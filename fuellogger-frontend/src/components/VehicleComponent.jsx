import React, { useState, useEffect } from 'react'
import { createVehicle, getVehicle, updateVehicle } from '../services/VehicleService'
import { useNavigate, useParams } from 'react-router-dom';

export function VehicleComponent() {
    const [vrm, setVrm] = useState('');
    const [make, setMake] = useState('');
    const [model, setModel] = useState('');
    const [fuelType, setFuelType] = useState('PETROL');

    const [errors, setErrors] = useState({
        vrm: '',
        make: '',
        model: '',
        fuelType: ''
    });

    const navigator = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            getVehicle(id).then((response) => {
                setVrm(response.data.vrm);
                setMake(response.data.make);
                setModel(response.data.model);
                setFuelType(response.data.fuelType);
            }).catch(error => {
                console.error(error);
            });
        }
    }, [id]);

    function saveOrUpdateVehicle(e) {
        e.preventDefault();

        if (validateForm()) {
            const vehicle = { vrm, make, model, fuelType };

            if (id) {
                updateVehicle(id, vehicle).then(() => {
                    navigator('/vehicles');
                }).catch(error => {
                    console.error(error);
                });
            } else {
                createVehicle(vehicle).then(() => {
                    navigator('/vehicles');
                }).catch(error => {
                    console.error(error);
                });
            }
        }
    }

    function validateForm() {
        let valid = true;
        const errorCopy = { ...errors };

        errorCopy.vrm = vrm.trim() ? '' : 'VRM (Registration) is required';
        errorCopy.make = make.trim() ? '' : 'Make is required';
        errorCopy.model = model.trim() ? '' : 'Model is required';
        errorCopy.fuelType = fuelType.trim() ? '' : 'Fuel Type is required';

        Object.values(errorCopy).forEach(err => {
            if (err) valid = false;
        });

        setErrors(errorCopy);
        return valid;
    }

    return (
        <div className='container mt-4'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 shadow'>
                    <h2 className='text-center mt-3'>{id ? 'Update Vehicle' : 'Add Vehicle'}</h2>
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>VRM / Registration Number:</label>
                                <input
                                    type='text'
                                    placeholder='e.g., AB12CDE'
                                    value={vrm}
                                    className={`form-control ${errors.vrm ? 'is-invalid' : ''}`}
                                    onChange={(e) => setVrm(e.target.value)}
                                />
                                {errors.vrm && <div className='invalid-feedback'>{errors.vrm}</div>}
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Make:</label>
                                <input
                                    type='text'
                                    placeholder='e.g., Ford'
                                    value={make}
                                    className={`form-control ${errors.make ? 'is-invalid' : ''}`}
                                    onChange={(e) => setMake(e.target.value)}
                                />
                                {errors.make && <div className='invalid-feedback'>{errors.make}</div>}
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Model:</label>
                                <input
                                    type='text'
                                    placeholder='e.g., Focus'
                                    value={model}
                                    className={`form-control ${errors.model ? 'is-invalid' : ''}`}
                                    onChange={(e) => setModel(e.target.value)}
                                />
                                {errors.model && <div className='invalid-feedback'>{errors.model}</div>}
                            </div>

                            <div className='form-group mb-3'>
                                <label className='form-label'>Fuel Type:</label>
                                <select
                                    value={fuelType}
                                    className='form-control'
                                    onChange={(e) => setFuelType(e.target.value)}
                                >
                                    <option value='PETROL'>PETROL</option>
                                    <option value='DIESEL'>DIESEL</option>
                                    <option value='ELECTRIC'>ELECTRIC</option>
                                    <option value='HYBRID'>HYBRID</option>
                                </select>
                            </div>

                            <button className='btn btn-success w-100' onClick={saveOrUpdateVehicle}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default VehicleComponent
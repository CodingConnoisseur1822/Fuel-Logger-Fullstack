import './App.css'
import HeaderComponent from './components/HeaderComponent'
import ListVehicleComponent from './components/ListVehicleComponent'
import VehicleComponent from './components/VehicleComponent'
import ListFuelLogComponent from './components/ListFuelLogComponent'
import FuelLogComponent from './components/FuelLogComponent'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <BrowserRouter>
      <HeaderComponent />
      <div className="container my-4">
        <Routes>
            {/* https://localhost:3000 */}
            <Route path='/' element={<ListVehicleComponent />} />
            {/* https://localhost:3000/vehicles */}
            <Route path='/vehicles' element={<ListVehicleComponent />} />
            {/* https://localhost:3000/add-vehicle */}
            <Route path='/add-vehicle' element={<VehicleComponent />} />
            {/* https://localhost:3000/edit-vehicle/:id */}
            <Route path='/edit-vehicle/:id' element={<VehicleComponent />} />
            {/* https://localhost:3000/vehicles/:vehicleId/fuel-logs */}
            <Route path='/vehicles/:vehicleId/fuel-logs' element={<ListFuelLogComponent />} />
            {/* https://localhost:3000/vehicles/:vehicleId/add-fuel-log */}
            <Route path='/vehicles/:vehicleId/add-fuel-log' element={<FuelLogComponent />} />
          </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App

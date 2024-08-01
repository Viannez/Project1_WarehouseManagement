import './App.css'
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import { BrowserRouter, Routes, Route, Link} from "react-router-dom";
import WarehousePage from './pages/WarehousePage';
import WarehouseDetails from './pages/WarehouseDetails';
import ProductPage from './pages/ProductPage';
import ProductDetails from './pages/ProductDetails';
import Navigation from './categories/Navigation/Navigation';

function App() {

  return (
    <div>

        <BrowserRouter>
          <Navigation className='nav'/>
          <Routes>
            <Route path='/' element={<WarehousePage />} />
            <Route path='/warehouse/:id/*' element={<WarehouseDetails />} />
            <Route path='/warehouse' element={<WarehousePage />} />
            <Route path='/product/:id/*' element={<ProductDetails />} />
            <Route path='/product' element={<ProductPage />} />
          </Routes>
        </BrowserRouter>
    </div>
    
  )
}

export default App

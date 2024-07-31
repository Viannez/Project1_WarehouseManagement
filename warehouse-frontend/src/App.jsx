import './App.css'
import WarehouseList from './categories/WarehouseList/WarehouseList'
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import { BrowserRouter, Routes, Route, Link} from "react-router-dom";
import WarehousePage from './pages/WarehousePage';
import ProductPage from './pages/ProductPage';
import Navigation from './categories/Navigation/Navigation';

function App() {

  return (
    <div>

        <BrowserRouter>
          {/* <nav>
            <Link to="/warehouse">Warehouse</Link>
            <Link to="/product">Product</Link>
          </nav> */}
          <Navigation/>
          <Routes>
            <Route path='/warehouse' element={<WarehousePage />} />
            <Route path='/product' element={<ProductPage />} />
            <Route />
          </Routes>
        </BrowserRouter>
    </div>
    
  )
}

export default App

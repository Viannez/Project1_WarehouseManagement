import './App.css'
import WarehouseList from './categories/WarehouseList/WarehouseList'
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import WarehousePage from './pages/WarehousePage';
import Layout from "./pages/Layout";

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<WarehousePage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App

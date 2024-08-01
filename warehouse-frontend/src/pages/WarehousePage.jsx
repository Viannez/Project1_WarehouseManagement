import WarehouseList from '../categories/Warehouse/WarehouseList'
import AddWarehouse from '../categories/Warehouse/AddWarehouse';
import WarehouseSearchBar from '../categories/Warehouse/WarehouseSearchBar';
import {SearchByID } from '../categories/Warehouse/WarehouseSearchBar';
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'

function WarehousePage() {
  
  return (
    <>
      <Header >
        <Title>
          <h1>Warehouses</h1>
        </Title>
        <AddWarehouse/>
        {/* <WarehouseSearchBar/> */}
        <SearchByID/>
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
        </GridContainer>
      </main>
      
    </>
  )
}

export default WarehousePage

import WarehouseSearchBar from '../categories/Warehouse/WarehouseSearchBar';
import {SearchByID } from '../categories/Warehouse/WarehouseSearchBar';
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import AddWarehouseModal from '../categories/Warehouse/AddWarehouseModal';

function WarehousePage() {
  
  return (
    <>
      <Header >
        <Title>
          <h1>Warehouses</h1>
        </Title>
        <AddWarehouseModal/>
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

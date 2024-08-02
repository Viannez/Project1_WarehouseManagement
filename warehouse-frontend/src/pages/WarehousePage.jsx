import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import AddWarehouseModal from '../categories/Warehouse/AddWarehouseModal';
import WarehouseList from '../categories/Warehouse/WarehouseList';

function WarehousePage() {
  
  return (
    <>
      <Header >
        <Title>
          <h1>Warehouses</h1>
        </Title>
        <AddWarehouseModal/>
        {/* <WarehouseSearchBar/> */}
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
        <WarehouseList/>
        </GridContainer>
      </main>
      
    </>
  )
}

export default WarehousePage

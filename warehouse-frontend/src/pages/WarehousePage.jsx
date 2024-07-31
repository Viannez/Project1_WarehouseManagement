import WarehouseList from '../categories/Warehouse/WarehouseList'
import WarehouseForm from '../categories/Warehouse/WarehouseForm';
import WarehouseSearch from '../categories/Warehouse/WarehouseSearch';
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'

function WarehousePage() {
  
  return (
    <>
      <Header >
        <Title>
          <h1>Warehouses</h1>
        </Title>
        <WarehouseForm/>
        <WarehouseSearch/>
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
          <WarehouseList >
          </WarehouseList>
        </GridContainer>
      </main>
      
    </>
  )
}

export default WarehousePage

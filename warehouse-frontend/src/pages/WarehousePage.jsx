import WarehouseList from '../categories/WarehouseList/WarehouseList'
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'

function WarehousePage() {

  return (
    <>
      <Header >
        <Title>
          <h1>Warehouses</h1>
          <h2>Display warehouses</h2>
        </Title>
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
          <WarehouseList >
            <span>Hello!</span>
            <strong>Friyay!</strong>
          </WarehouseList>
        </GridContainer>
      </main>
    </>
  )
}

export default WarehousePage

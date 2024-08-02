import { GridContainer, Header, Label, Select, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import AddWarehouseModal from '../categories/Warehouse/AddWarehouseModal';
import WarehouseList from '../categories/Warehouse/WarehouseList';
import { useState } from 'react';

//all warehouses and their cards
function WarehousePage() {
  let  [index, setIndex] = useState(1)
  function getIndex(e)
  {
    setIndex(e.target.value)
  }
  return (
    <>
      <Header >
        <Title>
          <h1>Warehouses</h1>
        </Title>
        <AddWarehouseModal/>
        <Label htmlFor="sort-warehouses">Sort warehouses by:</Label>
            <Select onChange={getIndex} id="sort-warehouse" name="sortWarehouse" required>
              <option >- Select -</option>
              <option value={1}> By ID</option>
              <option value={2}> By Capacity</option>
            </Select>
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
        <WarehouseList index={index}/>
        </GridContainer>
      </main>
      
    </>
  )
}

export default WarehousePage

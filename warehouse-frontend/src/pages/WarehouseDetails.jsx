import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css'; 
import '@trussworks/react-uswds/lib/index.css'
import WarehouseProductList from '../categories/Warehouse/WarehouseDetails/WarehouseProductList';
import WarehouseAddProductModal from '../categories/Warehouse/WarehouseDetails/WarehouseAddProductModal';
import WarehouseUpdateModal from '../categories/Warehouse/WarehouseDetails/WarehouseUpdateModal';
import { Col, Row } from 'react-bootstrap';

function WarehouseDetails() {

  const [warehouse, setWarehouse] = useState([]);
  const [loaded, setLoaded] = useState(false);

  const { id } = useParams()
  const capacityNums = {
    "inventoryCapacity":warehouse.inventoryCapacity,
    "capacity":warehouse.capacity,
  }

  console.log("cap:", capacityNums)
  const url = "http://localhost:8080/warehouse/"+id;


  useEffect(() => {
    fetch(url)
        .then(data => data.json()) // arrow function notation rules 
        .then(returnedData => {
            setWarehouse(returnedData);
            setLoaded(true);
        })
        .catch(err => { alert(err); console.log(err) })

  }, []) 

  return (
    <>
      <main className="container-center">
      <Title>
        <h1>Warehouse {id}</h1>
      </Title>
      <Row>
        <Col>
          <h3>Name: </h3>
          <h3> Location: </h3>
          <h3>Capacity filled: </h3>
        </Col>
        <Col>
          <h3> {warehouse.name}</h3>
          <h3> {warehouse.address}</h3>
          <h3> {warehouse.inventoryCapacity}/{warehouse.capacity}</h3>
        </Col>
        <Col></Col>
        <Col></Col>
      </Row>
        <WarehouseUpdateModal/>
        <WarehouseAddProductModal capacityNums={capacityNums}/>
      </main>
      <h3>This warehouse has the following products in stock:</h3>
      {
        loaded ?
        <WarehouseProductList productInventory={warehouse.productInventories}/> 
        : (<p colSpan='2'>Loading...</p>)
      }
      
    </>
  )
}

export default WarehouseDetails

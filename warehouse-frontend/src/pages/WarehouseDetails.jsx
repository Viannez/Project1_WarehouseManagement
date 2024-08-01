import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css'; 
import '@trussworks/react-uswds/lib/index.css'
import WarehouseProductList from '../categories/Warehouse/WarehouseDetails/WarehouseProductList';
import {WarehouseAddProduct} from '../categories/Warehouse/WarehouseDetails/WarehouseAddProduct';

function WarehouseDetails() {

  const [warehouse, setWarehouse] = useState([]);
  const [loaded, setLoaded] = useState(false);

  const { id } = useParams()

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
        <h3>Name: {warehouse.name}</h3>
        <h3> Location: {warehouse.address}</h3>
        <h3>Capacity filled: {warehouse.inventoryCapacity}/{warehouse.capacity}</h3>
        <WarehouseAddProduct inventoryCapacity={warehouse.inventoryCapacity} capacity={warehouse.capacity}/>
      </main>
      {
        loaded ?
        <WarehouseProductList productInventory={warehouse.productInventories}/> 
        : (<p colSpan='2'>Loading...</p>)
      }
      
    </>
  )
}

export default WarehouseDetails

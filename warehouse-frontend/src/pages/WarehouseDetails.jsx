import {SearchByID } from '../categories/Warehouse/WarehouseSearchBar';
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import { useState } from "react";
import { useEffect } from "react";
import { Route, useParams, Routes } from "react-router-dom";

import '@trussworks/react-uswds/lib/index.css'

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
          <h1>{warehouse.name}  {warehouse.address}  {warehouse.inventoryCapacity}/{warehouse.capacity}</h1>
        </Title>
      </main>
      
      
    </>
  )
}

export default WarehouseDetails

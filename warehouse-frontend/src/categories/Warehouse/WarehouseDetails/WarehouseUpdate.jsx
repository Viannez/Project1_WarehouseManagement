import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetWarehouses from "../../util/GetWarehouses";

export const WarehouseUpdate = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const { id } = useParams()
    let newWarehouseInventory;
    //get warehouses for selection box
    const warehouse = GetWarehouses(id);
    

    // submit data to add new warehouse inventory to 
    function handleSubmit(e) {
        const url = "http://localhost:8080/warehouse/"+id; 
        e.preventDefault();

        const data = new FormData(e.target);

        newWarehouseInventory = {
          name: data.get("warehouseName"),
          address: data.get("warehouseAddress"),
          capacity: data.get("warehouseCapacity") 
        }
        
        if(newWarehouseInventory.name==""){
          console.log("set name to: ", warehouse.name)
          newWarehouseInventory.name=warehouse.name;
        }
        if(newWarehouseInventory.address==""){
          console.log("set address to: ", warehouse.address)
          newWarehouseInventory.address=warehouse.address;
        }
        if(newWarehouseInventory.capacity==""){
          console.log("set capacity to: ", JSON.parse(warehouse.capacity))
          newWarehouseInventory.capacity=JSON.parse(warehouse.capacity);
        }

        //PUT request body for updating warehouse
        fetch(url, {
          method: "PUT",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify(newWarehouseInventory)
          })
          .then(data => data.json())
          .then((returnedData) => {
              console.log(returnedData)
              setMessage("Succesfully created new movie with id " + returnedData?.id)
          })
          .catch(err => {
              console.log(err);
              setError(err)
          });
        
        
    }

  return (
    <>
      <h4>Add New Warehouse to Warehouse</h4>
      <Form onSubmit={handleSubmit}>
      <div>
        <Label>Update Name</Label>
        <TextInput id="warehouse-name" name="warehouseName" type="text" />
        </div>
        <div>
          <Label>Update Address</Label>
          <TextInput id="warehouse-address" name="warehouseAddress" type="text"/>
        </div>
        <div>
          <Label>Update Capacity</Label>
          <TextInput id="warehouse-capacity" name="warehouseCapacity" type="number"/>
        </div>
        <Button type="submit">Submit</Button>
      </Form>
      {
        // TODO choose a nicer alert with a close button
        // make sure to reset the message and error state
        message && <Alert type="success" heading="Success status" headingLevel="h4">
          {message}
        </Alert>
      }

    </>
  )
}
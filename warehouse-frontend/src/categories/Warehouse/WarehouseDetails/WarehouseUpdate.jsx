import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetWarehouses from "../../Util/GetWarehouses";

export const WarehouseUpdate = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const { id } = useParams()
    let updateWarehouse;
    //get warehouses to keep set null attributes to previous values
    const warehouse = GetWarehouses(id);
    

    // submit data to add new warehouse inventory to 
    function handleSubmit(e) {
        const url = "http://localhost:8080/warehouse/"+id; 
        e.preventDefault();

        const data = new FormData(e.target);

        updateWarehouse = {
          name: data.get("warehouseName"),
          address: data.get("warehouseAddress"),
          capacity: data.get("warehouseCapacity") 
        }

        if(updateWarehouse.name==""){
          console.log("set name to: ", warehouse.name)
          updateWarehouse.name=warehouse.name;
        }
        if(updateWarehouse.address==""){
          console.log("set address to: ", warehouse.address)
          updateWarehouse.address=warehouse.address;
        }
        if(updateWarehouse.capacity==""){
          console.log("set capacity to: ", JSON.parse(warehouse.capacity))
          updateWarehouse.capacity=JSON.parse(warehouse.capacity);
        }

        if(updateWarehouse.capacity < warehouse.inventoryCapacity)
        {
          setMessage("Capacity is not enough to hold products!")
        }
        else{
          //PUT request body for updating warehouse
          setMessage("Success!")
          fetch(url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updateWarehouse)
            })
            .then(data => data.json())
            .then((returnedData) => {
                console.log(returnedData)
            })
            .catch(err => {
                console.log(err);
                setError(err)
            });

        }
        
        
    }

  return (
    <>
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
        message && <Alert type="success" headingLevel="h4">
          {message}
        </Alert>
      }

    </>
  )
}
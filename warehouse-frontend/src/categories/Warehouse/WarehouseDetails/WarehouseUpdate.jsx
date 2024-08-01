import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetWarehouses from "../../util/GetWarehouses";

export const WarehouseUpdate = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const { id } = useParams()

    //get warehouses for selection box
    const warehouse = GetWarehouses();
    

    // submit data to add new warehouse inventory to 
    // function handleSubmit(e) {
    //     const url = "http://localhost:8080/warehouse/"+id; 
    //     e.preventDefault();
    //     const data = new FormData(e.target);

    //     const newWarehouseInventory = {
    //       "name": 
    //     }
    //     console.log("id: ", id)
    //     console.log("p_id: ", data.get("warehouseID"))
    //     console.log("stock: ", typeof data.get("warehouseInventoryStock"))

    //     if(Number(inventoryCapacity)+Number(data.get("warehouseInventoryStock")) > capacity){
    //       console.log(inventoryCapacity+Number(data.get("warehouseInventoryStock")))
    //       //alert
    //       console.log("Too many warehouses!")
    //       //window.location.reload();
    //     }
    //     else
    //     {
    //       fetch(url, {
    //         method: "PUT",
    //         headers: {
    //             "Content-Type": "application/json"
    //         },
    //         body: JSON.stringify(newWarehouseInventory)
    //         })
    //         .then(data => data.json())
    //         .then((returnedData) => {
    //             console.log(returnedData)
    //             setMessage("Succesfully created new movie with id " + returnedData?.id)
    //         })
    //         .catch(err => {
    //             console.log(err);
    //             setError(err)
    //         });
    //     }
        
    // }

  return (
    <>
      <h4>Add New Warehouse to Warehouse</h4>
      <Form>
      <div>
        <Label>Update Name</Label>
        <TextInput id="inputs" name="warehouseName" type="text" />
        </div>
        <div>
          <Label>Update Address</Label>
          <TextInput id="inputs" name="warehouseAddress" type="number" value={warehouse.address}/>
        </div>
        <div>
          <Label>Update Capacity</Label>
          <TextInput id="inputs" name="warehouseCapacity" type="number" defaultValue={warehouse.capacity}/>
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
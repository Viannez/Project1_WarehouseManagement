import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";

const AddWarehouse = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    function handleSubmit(e) {
        const url = "http://localhost:8080/warehouse"; 
        // e.preventDefault();
        const data = new FormData(e.target);

        const newWarehouse = {
        name: data.get("warehouseName"),
        address: data.get("warehouseAddress"),
        capacity: data.get("warehouseCapacity")
        }

        e.target.reset();

        fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newWarehouse)
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
      <h4>Add New Warehouse</h4>
      <Form onSubmit={handleSubmit}>
        <Label htmlFor="warehouse-name">Warehouse Name</Label>
        <TextInput id="warehouse-name" name="warehouseName" type="text" />
        <div>
          <Label htmlFor="warehouse-address">Warehouse Address</Label>
          <TextInput id="warehouse-address" name="warehouseAddress" type="text" />
        </div>
        <div>
          <Label htmlFor="warehouse-capacity">Warehouse Cacpacity</Label>
          <TextInput id="warehouse-capacity" name="warehouseCapacity" type="number" />
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

export default AddWarehouse
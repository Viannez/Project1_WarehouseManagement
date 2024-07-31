import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";

const WarehouseSearch = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    function handleSubmit(e) {
        let urlI = "http://localhost:8080/warehouse/"; 
        e.preventDefault();
        const data = new FormData(e.target);

        const newWarehouse = {
        name: data.get("warehouseName"),
        }

        const url = urlI+ data.get("warehouseName")
        e.target.reset();

        fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
        })
        .then(data => data.json())
        .then((returnedData) => {
            console.log(returnedData)
            setMessage("Found!" + returnedData?.id)
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

export default WarehouseSearch
import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";

//create new warehouse
const AddWarehouse = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    function handleSubmit(e) {
        const url = "http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/warehouse"; 
        e.preventDefault();
        const data = new FormData(e.target);

        const newWarehouse = {
        name: data.get("warehouseName"),
        address: data.get("warehouseAddress"),
        capacity: data.get("warehouseCapacity")
        }
        
        console.log(newWarehouse)

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
          
            if(returnedData.status=='500')
            {
              setMessage("Failed, warehouse name already taken.")
            }
            //Added after presentation
            else if(returnedData.status=='400')
            {
              setMessage("Check inputs.")
            }
            //
            else{
              setMessage("Success!")
            } 
        })
        .catch(err => {
            console.log(err);
            setError("error: ", err)
        });
    }

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Label htmlFor="warehouse-name">Warehouse Name</Label>
        <TextInput id="warehouse-name" name="warehouseName" type="text" />
        <div>
          <Label htmlFor="warehouse-address">Warehouse Address</Label>
          <TextInput id="warehouse-address" name="warehouseAddress" type="text" />
        </div>
        <div>
          {/* typo fixed after presentation */}
          <Label htmlFor="warehouse-capacity">Warehouse Capacity</Label>
          <TextInput id="warehouse-capacity" name="warehouseCapacity" type="number" />
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

export default AddWarehouse
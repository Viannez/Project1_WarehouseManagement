import { useState } from "react";
import { useRef } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import WarehouseList from "./WarehouseList";

const WarehouseSearch = () => {

    const [warehouseAttribute, setEventId] = useState("")
    const [defaultState, setDefault] = useState(true)

    console.log("warehouse id: ", warehouseAttribute);
    console.log(defaultState)
    function handleSubmit(e) {
        e.preventDefault();
        const data = new FormData(e.target);
        setEventId(data.get("warehouseName"))
        setDefault(false)
        console.log("set id: ", warehouseAttribute);
        e.target.reset();

    }
    if(warehouseAttribute!="")
    {
        return(
            <WarehouseList 
        warehouseAttribute={warehouseAttribute}/>
        )
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
        defaultState &&
        <WarehouseList 
        warehouseAttribute={warehouseAttribute}/>
      }
      
    </>
  )
}

export default WarehouseSearch
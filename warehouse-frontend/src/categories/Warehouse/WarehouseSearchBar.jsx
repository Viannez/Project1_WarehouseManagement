import { useState } from "react";
import { Fragment } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import WarehouseList from "./WarehouseList";
import { Warehouse } from "./Warehouse";
import { Row } from "react-bootstrap";

 export default function WarehouseSearchBar(){

    const [warehouseAttribute, setEventId] = useState("")
    const [searchType, setSearchType] = useState("");

    // console.log("warehouse id: ", warehouseAttribute);
    console.log(searchType)
    function handleSubmit(e) {
        e.preventDefault();
        const data = new FormData(e.target);
        // setEventId(data.get("warehouseName"))
        // console.log("set id: ", warehouseAttribute);
        e.target.reset();
    }
    return (
    <>
      <h4>Add New Warehouse</h4> 
      <Form 
      class="usa-search"
      data-testid="form"
      role="search"
      onSubmit={handleSubmit}
      >
        <div class="container">
          <TextInput id="warehouse-name" class="usa-input" name="warehouseName" type="search" value=""/>
          <Button class="usa-button" type="submit">Submit</Button>
          <Select
        id="input-select"
        class="usa-select"
        onChange={(e) => setSearchType({searchType: e.target.value})}
      >
        <Fragment key=".0">
          <option>
            - Select way to search warehouses-{' '}
          </option>
          <option value="Id">
            Find by ID
          </option>
          <option value="Name">
            Search Names
          </option>
          <option value="Address">
            Search Addresses
          </option>
        </Fragment>
      </Select>
        </div>
      </Form>
     
      
    </>
  )
}

export function SearchByID()
{
  return(
    <WarehouseList/>
  )
}

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
      <Form 
      className="usa-search"
      data-testid="form"
      role="search"
      onSubmit={handleSubmit}
      >
        <div className="container">
          <TextInput id="warehouse-name" className="usa-input" name="warehouseName" type="search"/>
          <Button className="usa-button" type="submit">Search</Button>
          <Select
          id="input-select"
          className="usa-select"
          onChange={(e) => setSearchType({searchType: e.target.value})}>
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

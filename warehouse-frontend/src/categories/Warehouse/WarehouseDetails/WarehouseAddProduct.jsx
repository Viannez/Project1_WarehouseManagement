import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetProducts from "../../Util/GetProducts";

export const WarehouseAddProduct = ({capacityNums}) => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const { id } = useParams()

    //get products for selection box
    const products = GetProducts();

    //submit data to add new product inventory to 
    function handleSubmit(e) {
        const url = "http://localhost:8080/product_inventory"; 
        e.preventDefault();
        const data = new FormData(e.target);

        const newProductInventory = {
        warehouse: {
          id:id
        },
        product: {
          id:data.get("productID")
        },
        stock: data.get("productInventoryStock")
        }
        console.log("id: ", id)
        console.log("p_id: ", data.get("productID"))
        console.log("capacity: ",  capacityNums)

        console.log(capacityNums.inventoryCapacity+Number(data.get("productInventoryStock")))

        if(Number(capacityNums.inventoryCapacity)+Number(data.get("productInventoryStock")) > capacityNums.capacity){
          //alert
          console.log("Too many products!")
          //window.location.reload();
        }
        else
        {
          fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newProductInventory)
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
        
    }

  return (
    <>
      <h4>Add New Product to Warehouse</h4>
      <Form onSubmit={handleSubmit}>
      <div>
        <Label>Product ID</Label>
            <Select id="product-id" name="productID" required>
                <option>- Select -</option>
                {
                  products.map( ({id, name}) => 
                    <option key={id} value={id}>{'(id: ' + id +')  ' + name}</option> )
                }
            </Select>
        </div>
        <div>
          <Label>Product Stock in this warehouse</Label>
          <TextInput id="inputs" name="productInventoryStock" type="number" />
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
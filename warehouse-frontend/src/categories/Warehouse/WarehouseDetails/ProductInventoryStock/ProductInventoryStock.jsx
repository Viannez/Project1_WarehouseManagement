import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetProducts from "../../../Util/GetProducts";
import GetWarehouses from "../../../Util/GetWarehouses";
import GetProductInventories from "../../../Util/GetProductInventories";

export const ProductInventoryStock = ({info}) => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const { id } = useParams()

    let updateProductInventory;

    //get product for previous values
    const product = info.product;
    console.log("product: ", product)

    //get productInventory for warehouse id
    const productInventory = info.productInventory;
    console.log("productInventory: ", productInventory)

    
    //get warehouse for nested values
    const warehouse = GetWarehouses(id)
    console.log("got warehouse: ", warehouse)
    
   

    // submit data to update productInventory
    function handleSubmit(e) {
        const url = "http://localhost:8080/product_inventory/"+productInventory.id; 
        e.preventDefault();

        const data = new FormData(e.target);
        
        //request body
        updateProductInventory = {
          warehouse: {
            id: warehouse.id,
            address: warehouse.address,
            name: warehouse.name,
            capacity: warehouse.capacity
          },
          product: {
            id: product.id,
            name: product.name,
            category: 
            {
              id: product.category,
              name: product.categoryName
            },
            price: product.price
          },
          stock: data.get("productInventoryStock") 
        }
        //
        if(data.get("productInventoryStock")==""){
          updateProductInventory.stock=0;
        }
        console.log("ALL INFO: ", updateProductInventory)

        
        const inventoryCapacityNow =  warehouse.inventoryCapacity-productInventory.stock+parseInt(updateProductInventory.stock);
        console.log("inventoryCapacity now: ",  inventoryCapacityNow)

        if(inventoryCapacityNow> warehouse.capacity)
        {
          setMessage("Too many products")
        }
        else{
          setMessage("Success!")
          fetch(url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updateProductInventory)
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
          <Label>Stock</Label>
          <TextInput id="productInventory-price" name="productInventoryStock" type="number"/>
        </div>
        <Button type="submit">Submit</Button>
      </Form>
      {
        <Alert type="success" headingLevel="h4">
          {message}
        </Alert>
      }

    </>
  )
}
import { useState } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetWarehouses from "../../../Utils/GetWarehouses";

//Update stock of product in warehouse
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
        const url = `http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/product_inventory/`+productInventory.id; 
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

        setMessage("Success!")
        //check for products inventory total
        if(inventoryCapacityNow> warehouse.capacity)
        {
          setMessage("Failed, too many products!")
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
                if(returnedData.status=='500')
                  {
                    setMessage("Failed, price cannot be below 0.")
                  }
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
          <TextInput id="update-product-stock" name="productInventoryStock" type="number"/>
        </div>
        <Button id='submit-stock-button' type="submit">Submit</Button>
      </Form>
      {
        <Alert type="success" headingLevel="h4">
          {message}
        </Alert>
      }

    </>
  )
}
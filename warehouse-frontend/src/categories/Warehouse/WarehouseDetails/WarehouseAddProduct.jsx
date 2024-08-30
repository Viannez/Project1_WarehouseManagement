import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetProducts from "../../Utils/GetProducts";

//add product to warehouse
export const WarehouseAddProduct = ({capacityNums}) => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const { id } = useParams()

    //get products for selection box
    const products = GetProducts();

    //submit data to add new product inventory to 
    function handleSubmit(e) {
        const url = "http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/product_inventory"; 
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
          setMessage("Too many products!")
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
              if(returnedData.status=='500')
                {
                  setMessage("Failed, the product exists in warehouse.")
                }
                else{
                  setMessage("Success!")
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
        <Label>Product Name</Label>
            <Select id="product-id" name="productID" required>
                {
                  products.map( ({id, name}) => 
                    <option key={id} value={id}>{name}</option> )
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
        message && <Alert type="success" headingLevel="h4">
          {message}
        </Alert>
      }

    </>
  )
}
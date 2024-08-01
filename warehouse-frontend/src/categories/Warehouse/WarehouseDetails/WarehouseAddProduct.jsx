import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";

export const WarehouseAddProduct = ({inventoryCapacity, capacity}) => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const [products, setProducts] = useState([]);
    const { id } = useParams()

    const url = "http://localhost:8080/product";
    useEffect(() => {

        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setProducts(returnedData);
                // setLoaded(true);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 

    console.log("products: ", products)
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
        console.log("stock: ", data.get("productInventoryStock"))

        if(inventoryCapacity+Number(data.get("productInventoryStock")) > capacity){
          console.log(inventoryCapacity+Number(data.get("productInventoryStock")))
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
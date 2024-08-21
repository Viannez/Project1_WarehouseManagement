import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import GetCategories from "../Utils/GetCategories";

//create new product 
const AddProduct = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    //get categories to select from
    const categories = GetCategories();

    function handleSubmit(e) {
        const url = "http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com:8080/product"; 
        e.preventDefault();
        const data = new FormData(e.target);

        const newProduct = {
        name: data.get("productName"),
        category: {
            id: data.get("productCategory")
        },
        price: data.get("productPrice")
        }

        console.log(newProduct.category)

        fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newProduct)
        })
        .then(data => data.json())
        .then((returnedData) => {
          //Added after presentation
          if(returnedData.status=='400'){
            setMessage("Check inputs.")
          }
          else if(returnedData.status=='500'){
            setMessage("Product name already taken.")
          }
          else{
            setMessage("Success!")
          } 
        })
        .catch(err => {
            console.log(err);
            setError(err)
            setMessage("Check inputs for error")
        });
        
        
    }

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Label htmlFor="product-name">Product Name</Label>
        <TextInput id="product-name" name="productName" type="text" />
        <div>
            <Label htmlFor="product-category">Category</Label>
              <Select id="product-category" name="productCategory" required>
              {
                categories.map( ({id, name}) => 
                  <option key={id} value={id}>{name}</option> )
              }
              </Select>
        </div>
        <div>
          <Label htmlFor="product-price">Product Price ($)</Label>
          <TextInput id="product-price" name="productPrice" type="number" />
        </div>
        <Button type="submit">Submit</Button>
      </Form>
      {
        message && <Alert type="success"  headingLevel="h4">
          {message}
        </Alert>
      }

    </>
  )
}

export default AddProduct
import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import GetCategories from "../util/GetCategories";

const AddProduct = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const categories = GetCategories();

    function handleSubmit(e) {
        const url = "http://localhost:8080/product"; 
        // e.preventDefault();
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
            console.log(returnedData)
            setMessage("Succesfully created new movie with id " + returnedData?.id)
        })
        .catch(err => {
            console.log(err);
            setError(err)
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
                    <option>- Select -</option>
                {
                  categories.map( ({id, name}) => 
                    <option key={id} value={id}>{'(id: ' + id +')  ' + name}</option> )
                }
                </Select>
        </div>
        <div>
          <Label htmlFor="product-capacity">Product Price ($)</Label>
          <TextInput id="product-capacity" name="productPrice" type="number" />
        </div>
        <Button type="submit">Submit</Button>
      </Form>
      {
        message && <Alert type="success" heading="Success status" headingLevel="h4">
          {message}
        </Alert>
      }

    </>
  )
}

export default AddProduct
import { useState } from "react";
import { useEffect } from "react";
import { Label, TextInput, Form, Button, Select, Alert } from "@trussworks/react-uswds";
import { useParams } from "react-router-dom";
import GetCategories from "../../Util/GetCategories";
import GetProducts from "../../Util/GetProducts";

export const ProductUpdate = () => {
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const { id } = useParams()
    let updateProduct;

    //get product for previous values
    const product = GetProducts(id);

    //get categories for selection box
    const categories = GetCategories();
    

    // submit data to update product
    function handleSubmit(e) {
        const url = "http://localhost:8080/product/"+id; 
        e.preventDefault();

        const data = new FormData(e.target);
        

        updateProduct = {
          name: data.get("productName"),
          category: 
          {
            id: data.get("productCategory"),
            name: ""
          },
          price: data.get("productPrice") 
        }


        if(updateProduct.name==""){
          console.log("set name to: ", product.name)
          updateProduct.name=product.name;
        }
        console.log("category id: ", updateProduct.category.id)
        if(updateProduct.category.id=="- Select -"){
          console.log("set category to: ", JSON.parse(product.category))
          updateProduct.category.id=JSON.parse(product.category);
        }
        if(updateProduct.price==""){
          console.log("set price to: ", JSON.parse(product.price))
          updateProduct.price=JSON.parse(product.price);
        }
        //find correct category name by id
        let result = categories.filter(category => {
          return category.id === parseInt(updateProduct.category.id)
        })
        updateProduct.category.name=result[0].name;
        console.log(result)
        console.log("set category name to: ", result[0].name)

        console.log("updateProduct: ", updateProduct)
        // PUT request body for updating product
        fetch(url, {
          method: "PUT",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify(updateProduct)
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
      <div>
        <Label>Update Name</Label>
        <TextInput id="product-name" name="productName" type="text" />
        </div>
        <Label htmlFor="product-category">Update Category</Label>
                <Select id="product-category" name="productCategory" required>
                    <option>- Select -</option>
                {
                  categories.map( ({id, name}) => 
                    <option key={id} value={id}>{'(id: ' + id +')  ' + name}</option> )
                }
                </Select>
        <div>
          <Label>Update Price</Label>
          <TextInput id="product-price" name="productPrice" type="number"/>
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
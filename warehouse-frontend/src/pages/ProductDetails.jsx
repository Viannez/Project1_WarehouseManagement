import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";

import '@trussworks/react-uswds/lib/index.css'
// import ProductWarehouseList from '../categories/Product/ProductDetails/ProductWarehouseList';
// import {ProductAddProduct} from '../categories/Product/ProductDetails/ProductAddProduct';
// import Example from '../categories/Product/ProductDetails/EditProduct';

function ProductDetails() {

  const [product, setProduct] = useState([]);
  const [loaded, setLoaded] = useState(false);

  const { id } = useParams()

  const url = "http://localhost:8080/product/"+id;


  useEffect(() => {
    fetch(url)
        .then(data => data.json()) // arrow function notation rules 
        .then(returnedData => {
            setProduct(returnedData);
            setLoaded(true);
        })
        .catch(err => { alert(err); console.log(err) })

  }, []) 
  console.log(product.id)
  console.log(product.productInventories)
  return (
    <>
      <main className="container-center">
      <Title>
        <h1>Product {id}: {product.name}</h1>
      </Title>
        {/* <h2> Location: {product.address}</h2>
        <h2>Capacity filled: {product.inventoryCapacity}/{product.capacity}</h2>
        <ProductAddProduct inventoryCapacity={product.inventoryCapacity} capacity={product.capacity}/> */}
        {/* <Example/> */}
      </main>
      {/* {
        loaded ?
        <ProductWarehouseList productInventory={product.productInventories}/> 
        : (<p colSpan='2'>Loading...</p>)
      } */}
      
    </>
  )
}

export default ProductDetails

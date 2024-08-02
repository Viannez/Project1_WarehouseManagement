import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import ProductUpdateModal from '../categories/Product/ProductDetails/ProductUpdateModal';

import '@trussworks/react-uswds/lib/index.css'

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
  console.log("product: ", product)
  return (
    <>
      <main className="container-center">
      <Title>
        <h1>Product {id}</h1>
      </Title>
        <h3>Name: {product.name}</h3>
        <h3>Size: {product.categoryName}</h3>
        <h3>Price: ${product.price}</h3>
        <ProductUpdateModal/>
      </main>
      {/* {
        loaded ?
        <ProductProductList productInventory={warehouse.productInventories}/> 
        : (<p colSpan='2'>Loading...</p>)
      }
       */}
    </>
  )
}

export default ProductDetails

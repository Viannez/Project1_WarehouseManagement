import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import ProductUpdateModal from '../categories/Product/ProductDetails/ProductUpdateModal';

import '@trussworks/react-uswds/lib/index.css'
import ProductWarehouseList from '../categories/Product/ProductDetails/ProductWarehouseList';
import { Row, Col } from 'react-bootstrap';

//Page about one product linked from card
function ProductDetails() {

  const [product, setProduct] = useState([]);
  const [loaded, setLoaded] = useState(false);

  const { id } = useParams()

  const url = "http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/product/"+id;


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
      <Row>
      <Col>
          <h3>Name: </h3>
          <h3>Size: </h3>
          <h3>Price: </h3>
        </Col>
        <Col style={{color:'lightskyblue'}}>
          <h3>{product.name}</h3>
          <h3>{product.categoryName}</h3>
          <h3>${product.price}</h3>
        </Col>
        <Col>
        </Col>
        <Col>
        </Col>
      </Row>
      <ProductUpdateModal/>
      </main>
      <h3>This product is located in the following warehouses:</h3>
      {
        loaded ?
        <ProductWarehouseList productInventory={product.productInventories}/> 
        : (<p colSpan='2'>Loading...</p>)
      }
      
    </>
  )
}

export default ProductDetails

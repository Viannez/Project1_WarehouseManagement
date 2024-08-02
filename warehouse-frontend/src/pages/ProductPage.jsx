import ProductList from '../categories/Product/ProductList'
import { GridContainer, Header, Title, Label, Select } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import AddProduct from '../categories/Product/AddProduct'
import AddProductModal from '../categories/Product/AddProductModal'
import { useState } from 'react'

//All products page
function ProductPage() {
  let  [index, setIndex] = useState(1)
  function getIndex(e)
  {
    setIndex(e.target.value)
  }
  return (
    <>
      <Header >
        <Title>
          <h1>Products</h1>
        </Title>
        <AddProductModal/>
          <Label htmlFor="sort-products">Sort products by:</Label>
            <Select onChange={getIndex} id="sort-product" name="sortProduct" required>
              <option >- Select -</option>
              <option value={1}> By ID</option>
              <option value={2}> By Price</option>
            </Select>
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
          <ProductList index={index}/>
        </GridContainer>
      </main>
    </>
  )
}

export default ProductPage

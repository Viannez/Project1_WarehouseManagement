import ProductList from '../categories/Product/ProductList'
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import ProductForm from '../categories/Product/ProductForm'

function ProductPage() {

  return (
    <>
      <Header >
        <Title>
          <h1>Products</h1>
        </Title>
        <ProductForm/>
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
          <ProductList >
          </ProductList>
        </GridContainer>
      </main>
    </>
  )
}

export default ProductPage

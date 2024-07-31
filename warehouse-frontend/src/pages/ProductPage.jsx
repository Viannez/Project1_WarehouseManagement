import ProductList from '../categories/ProductList.jsx/ProductList'
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'

function ProductPage() {

  return (
    <>
      <Header >
        <Title>
          <h1>Products</h1>
          <h2>Display products</h2>
        </Title>
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

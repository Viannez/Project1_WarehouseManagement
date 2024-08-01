import ProductList from '../categories/Product/ProductList'
import { GridContainer, Header, Title } from '@trussworks/react-uswds'
import '@trussworks/react-uswds/lib/index.css'
import AddProduct from '../categories/Product/AddProduct'
import AddProductModal from '../categories/Product/AddProductModal'
function ProductPage() {

  return (
    <>
      <Header >
        <Title>
          <h1>Products</h1>
          <AddProductModal/>
        </Title>
      </Header>
      <main className="container-center">
        <GridContainer containerSize="desktop">
          <ProductList/>
        </GridContainer>
      </main>
    </>
  )
}

export default ProductPage

import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import { ProductInventoryStock} from './ProductInventoryStock';

function ProductInventoryStockModal({info}) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Update Product Stock
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Update Product Stock</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <ProductInventoryStock info={info}/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default ProductInventoryStockModal
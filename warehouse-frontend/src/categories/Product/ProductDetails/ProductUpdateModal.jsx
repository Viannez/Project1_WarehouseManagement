import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import { ProductUpdate } from './ProductUpdate';
import { FaPen } from "react-icons/fa";

//Displays Product update form in Modal
function ProductUpdateModal() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <Button id="update-product" variant="primary" onClick={handleShow} style={{height:'90px'}}>
        <FaPen/>
      </Button>
      <Modal id="update-product-modal" show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Update Product</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <ProductUpdate/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default ProductUpdateModal
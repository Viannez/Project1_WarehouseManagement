import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import AddProduct from './AddProduct';

function AddProductModal
() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Add Product
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add new Product</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you are reading this text in a modal!
          <AddProduct/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default AddProductModal
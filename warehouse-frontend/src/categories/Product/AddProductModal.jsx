import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import AddProduct from './AddProduct';
import { FaRegPlusSquare } from "react-icons/fa";

function AddProductModal
() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <Button variant="primary" onClick={handleShow} style={{height:'90px'}}>
        <FaRegPlusSquare />
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add new Product</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <AddProduct/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default AddProductModal
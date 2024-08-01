import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import { WarehouseAddProduct } from './WarehouseAddProduct';

function WarehouseAddProductModal({capacityNums}) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  console.log("help", capacityNums)
  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Add Product to Warehouse
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add new Product Inventory to warehouse</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <WarehouseAddProduct capacityNums={capacityNums}/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default WarehouseAddProductModal
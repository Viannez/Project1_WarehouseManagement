import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import { WarehouseUpdate } from './WarehouseUpdate';
import { FaPen } from 'react-icons/fa';

function WarehouseUpdateModal(inventoryCapacity, capacity) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <Button variant="primary" onClick={handleShow} style={{height:'90px'}}>
        <FaPen/>
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Update Warehouse</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <WarehouseUpdate/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default WarehouseUpdateModal
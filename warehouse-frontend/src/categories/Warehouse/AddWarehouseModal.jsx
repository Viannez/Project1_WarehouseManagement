import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import AddWarehouse from './AddWarehouse';
import { FaRegPlusSquare } from "react-icons/fa";

//create new warehouse form in modal
function AddWarehouseModal
() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
      <Button id="add-warehouse" variant="primary" onClick={handleShow} style={{height:'90px'}}>
        <FaRegPlusSquare />
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add new Warehouse</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <AddWarehouse/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default AddWarehouseModal
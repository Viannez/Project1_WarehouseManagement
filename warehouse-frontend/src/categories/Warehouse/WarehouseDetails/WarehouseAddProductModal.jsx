import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import { WarehouseAddProduct } from './WarehouseAddProduct';
import { FaRegPlusSquare } from "react-icons/fa";

//add product form in modal
function WarehouseAddProductModal({capacityNums}) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  console.log("help", capacityNums)
  return (
    <>
      <Button id="warehouse-add-product" variant="primary" onClick={handleShow} style={{height:'90px'}}>
        <FaRegPlusSquare />
      </Button>
      <Modal id="warehouse-add-product-modal"show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Product to Warehouse</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <WarehouseAddProduct capacityNums={capacityNums}/>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default WarehouseAddProductModal
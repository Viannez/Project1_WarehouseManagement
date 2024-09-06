import { useState } from 'react';
import { Button } from '@trussworks/react-uswds';
import Modal from 'react-bootstrap/Modal';
import { ProductInventoryStock} from './ProductInventoryStock';
import { FaPen } from "react-icons/fa";

//update stock form in modal
function ProductInventoryStockModal({info}) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <>
        <Button id="update-product-stock" variant="primary" onClick={handleShow} style={{height:'90px'}}>
            <FaPen/>
        </Button>
        <Modal id='product-stock-modal' show={show} onHide={handleClose}>
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
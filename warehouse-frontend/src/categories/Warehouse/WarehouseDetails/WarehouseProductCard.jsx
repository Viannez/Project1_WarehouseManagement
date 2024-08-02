import { Card, CardHeader, Link, Button} from '@trussworks/react-uswds';
import GetProducts from "../../Util/GetProducts";
import ProductInventoryStockModal from "./ProductInventoryStock/ProductInventoryStockModal";

import { FaRegTrashAlt } from "react-icons/fa";
import { FaArrowRightToBracket } from "react-icons/fa6";

//a product within warehouse with stock
export const WarehouseProductCard = ({productInventory}) => {

    //get product from productInventory
    const product = GetProducts(productInventory.product)

    //pass info to modal
    const passToStock =
    {
        product:product,
        productInventory:productInventory
    }
    console.log("info: ", passToStock)

    function handleDelete(e) {
        const url = "http://localhost:8080/product_inventory/"+productInventory.id; 

        fetch(url, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        },
        })
        .then(data => data.json())
        .then((returnedData) => {
            console.log(returnedData)
        })
        .catch(err => {
            console.log(err);
        });
        window.location.reload();
    }
    return (
        <>
            <Card layout="flagMediaRight" gridLayout={{
            tablet: {
            col: 3
            }
            }} containerProps={{
                className: 'border-primary-vivid'
            }}>
                <CardHeader>
                    <div className="container">
                        <div className="container-element"> {product.name} </div>
                        <div className="container-element"> ${product.price}</div>
                        <div className="container-element"> Size:{product.categoryName}</div>
                        <div className="container-element">Stock: {productInventory.stock}</div>
                        <ProductInventoryStockModal info={passToStock}/>
                        <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/product/'+product.id} >
                            <div className='container-button' style={{textAlign:'left'}}>
                                <FaArrowRightToBracket />
                            </div>
                        </Link>
                        <Button onClick={handleDelete} className="usa-button">
                            <FaRegTrashAlt />
                        </Button>
                    </div>
                </CardHeader>
            </Card>
        </>
    )
}

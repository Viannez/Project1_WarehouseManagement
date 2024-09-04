import { Card, CardHeader, Link, Button} from '@trussworks/react-uswds';
import GetWarehouses from "../../Utils/GetWarehouses";
import { FaRegTrashAlt } from "react-icons/fa";
import { FaArrowRightToBracket } from "react-icons/fa6";

//Shows warehouse that product exists in
export const ProductWarehouseCard = ({productInventory}) => {
    
    const warehouse = GetWarehouses(productInventory.warehouse)

    function handleClick(e) {
        const url = `${import.meta.env.VITE_APP_API_ENDPOINT}/product_inventory/`+productInventory.id; 

        fetch(url, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        },
        })
        .then(data => {data ? JSON.parse(data) : {}})
        .then((returnedData) => {
            console.log(returnedData)
        })
        .catch(err => {
            console.log(err);
        })
        .then(() => {
            window.location.reload();
        });
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
                    <div className="container-element"> {warehouse.name} </div>
                    <div className="container-element"> ID: {warehouse.id}</div>
                    <div className="container-element">Stock: {productInventory.stock}</div>
                    <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/warehouse/'+warehouse.id} >
                        <div className='container-button' style={{textAlign:'left'}}>
                            <FaArrowRightToBracket />
                        </div>
                    </Link>
                    <Button onClick={handleClick} className="usa-button">
                        <FaRegTrashAlt />
                    </Button>
                </div>
                </CardHeader>
            </Card>
        </>
    )
}

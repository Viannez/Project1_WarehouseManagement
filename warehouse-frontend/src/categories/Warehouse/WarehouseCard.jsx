import { Card, CardHeader, Link, Button} from '@trussworks/react-uswds';
import '../styles/Card.css'
import '../styles/Button.css'
import { FaMagnifyingGlass } from "react-icons/fa6";

import { FaRegTrashAlt } from "react-icons/fa";

//warehouse information on a card
export const WarehouseCard = ({warehouse}) => {
    
    //DELETE Warehouse
    function handleClick(e) {
        const url = "http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/warehouse/"+warehouse.id; 

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
                        <div id="warehouse-name" className="container-element">{warehouse.name}</div>
                        <div id="warehouse-id"className="container-element">ID: {warehouse.id}</div>
                        <div id="warehouse-address" className="container-element" > {warehouse.address} </div>
                        <div id="warehouse-capacity" className="container-element" > {warehouse.inventoryCapacity}/{warehouse.capacity} </div >
                        <Link className="usa-button" variant="unstyled" href={'/warehouse/'+warehouse.id} >
                        <div id="warehouse-details" className='container-button' style={{textAlign:'left'}}>
                            <FaMagnifyingGlass/>
                        </div>
                        </Link>
                        <Button id="delete-warehouse" onClick={handleClick} >
                            <FaRegTrashAlt />
                        </Button>
                    </div>
                </CardHeader>
            </Card>
        </>
    )
}

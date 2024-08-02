import { Card, CardHeader, Link, Button} from '@trussworks/react-uswds';
import '../styles/Card.css'
import '../styles/Button.css'

import { FaRegTrashAlt } from "react-icons/fa";

export const WarehouseCard = ({warehouse}) => {
    
    function handleClick(e) {


        const url = "http://localhost:8080/warehouse/"+warehouse.id; 

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
                        <div className="container-element">{warehouse.name}</div>
                        <div className="container-element">ID: {warehouse.id}</div>
                        <div className="container-element" > {warehouse.address} </div>
                        <div className="container-element" > {warehouse.inventoryCapacity}/{warehouse.capacity} </div >
                        <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/warehouse/'+warehouse.id} >
                            View
                        </Link>
                        <Button onClick={handleClick} >
                        <FaRegTrashAlt />
                        </Button>
                    </div>
                </CardHeader>
            </Card>
        </>
    )
}

import { useState } from "react";
import { useRef } from "react";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import { Card, CardHeader, CardMedia, CardBody, CardFooter, Link, Button} from '@trussworks/react-uswds';
import WarehouseSinglePage from '../../pages/WarehouseDetails';
import './Warehouse.css'

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
                            Open Warehouse 
                        </Link>
                        <Button onClick={handleClick} className="usa-button">Delete</Button>
                    </div>
                </CardHeader>
            </Card>
        </>
    )
}

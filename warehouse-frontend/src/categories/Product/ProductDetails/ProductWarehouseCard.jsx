import { useState } from "react";
import { useEffect } from "react";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import { Card, CardHeader, CardMedia, CardBody, CardFooter, Link, Button} from '@trussworks/react-uswds';
import GetProducts from "../../Util/GetProducts";

export const WarehouseProductCard = ({productInventory}) => {
    
    const product = GetProducts(productInventory.product)

    function handleClick(e) {
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
                    <div className="container-element">Stock: {productInventory.stock}</div>
                    <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/product/'+product.id} >
                        Open Product 
                    </Link>
                    <Button onClick={handleClick} className="usa-button">Delete</Button>
                </div>
                </CardHeader>
            </Card>
        </>
    )
}

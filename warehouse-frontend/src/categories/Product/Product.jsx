import { useState } from "react";
import { useRef } from "react";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import { Card, CardHeader, CardMedia, CardBody, CardFooter, Link, Button} from '@trussworks/react-uswds';
// import ProductSinglePage from '../../pages/ProductDetails';
// import './Product.css'

export const Product = ({product}) => {
    
    function handleClick(e) {


        const url = "http://localhost:8080/product/"+product.id; 

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
                        <h3 className="usa-card__heading">{product.name}</h3>
                        <h3 className="usa-card__heading">ID: {product.id}</h3>
                        <Button onClick={handleClick} className="usa-button">Delete</Button>
                    </div>
                </CardHeader>
                <CardBody>
                    <div className="container">
                        <p > {product.address} </p>
                        <p > {product.price} </p>
                        <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/product/'+product.id} >
                            Open Product 
                        </Link>
                    </div>
                </CardBody>
            </Card>
        </>
    )
}

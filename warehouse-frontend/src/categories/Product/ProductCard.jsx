import { useState } from "react";
import { useRef } from "react";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import { Card, CardHeader, CardMedia, CardBody, CardFooter, Link, Button} from '@trussworks/react-uswds';
// import ProductSinglePage from '../../pages/ProductDetails';

export const ProductCard = ({product}) => {
    
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
                        <div className="container-element">{product.name}</div>
                        <div className="container-element">ID: {product.id}</div>
                        <div className="container-element"> {product.address} </div>
                        <div className="container-element"> {product.price} </div>
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

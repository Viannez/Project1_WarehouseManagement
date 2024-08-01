import { useState } from "react";
import { useEffect } from "react";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import { Card, CardHeader, CardMedia, CardBody, CardFooter, Link, Button} from '@trussworks/react-uswds';

export const WarehouseProductCard = ({productInventory}) => {
    const url = "http://localhost:8080/product/"+productInventory.product;

    const [product, setProduct] = useState([]);
    useEffect(() => {
        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setProduct(returnedData);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 

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
                        <div className="container-element">ID: {productInventory.id}</div>
                        <div className="container-element">Stock: {productInventory.stock}</div>
                        <div className="container-element"> {product.name} </div>
                        <div className="container-element"> ${product.price}</div>
                        <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/productInventory/'+product.id} >
                            Open Product 
                        </Link>
                        <Button onClick={handleClick} className="usa-button">Delete</Button>
                    </div>
                </CardHeader>
            </Card>
        </>
    )
}

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
                        <h3 className="usa-card__heading">ID: {productInventory.id}</h3>
                        <h3 className="usa-card__heading">Stock: {productInventory.stock}</h3>
                        <Button onClick={handleClick} className="usa-button">Delete</Button>
                    </div>
                </CardHeader>
                <CardBody>
                    <div className="container">
                        <p > {product.name} </p>
                        <p > ${product.price}</p>
                        <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/productInventory/'+product.id} >
                            Open Product 
                        </Link>
                    </div>
                </CardBody>
            </Card>
        </>
    )
}

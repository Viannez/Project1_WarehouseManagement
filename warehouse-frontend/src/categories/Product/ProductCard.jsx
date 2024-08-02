import { Card, CardHeader, Link, Button} from '@trussworks/react-uswds';
import { FaRegTrashAlt } from "react-icons/fa";

export const ProductCard = ({product}) => {
    
    //DELETE Product from DB
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
                        <div className="container-element"> ${product.price} </div>
                        <div className="container-element">Size: {product.categoryName} </div>
                        <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/product/'+product.id} >
                            Open Product 
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

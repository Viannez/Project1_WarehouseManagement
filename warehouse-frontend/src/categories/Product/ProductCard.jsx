import { Card, CardHeader, Link, Button} from '@trussworks/react-uswds';
import { FaRegTrashAlt } from "react-icons/fa";
import { FaMagnifyingGlass } from "react-icons/fa6";

//Product with product information 
export const ProductCard = ({product}) => {

    //DELETE Product from DB
    function handleClick(e) {
        const url = `http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/product/`+product.id; 

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
                        <div id="product-name" className="container-element">{product.name}</div>
                        <div id="product-id" className="container-element">ID: {product.id}</div>
                        <div id="product-price" className="container-element"> ${product.price} </div>
                        <div id="product-category" className="container-element">Size: {product.categoryName} </div>
                        <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'/product/'+product.id} >
                            <div id="product-details" className='container-button' style={{textAlign:'left'}}>
                                <FaMagnifyingGlass/>
                            </div>
                        </Link>
                        <Button id="delete-product" onClick={handleClick} className="usa-button">
                            <FaRegTrashAlt />
                        </Button>
                    </div>
                </CardHeader>
            </Card>
        </>
    )
}

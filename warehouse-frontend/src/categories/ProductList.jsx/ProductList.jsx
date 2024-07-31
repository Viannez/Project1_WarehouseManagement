import { useState } from "react";
import { useEffect } from "react";
import { Product } from "../Product/Product";


const ProductList = () => {
    const url = "http://localhost:8080/product";

    const [products, setProducts] = useState([]);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {


        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setProducts(returnedData);
                setLoaded(true);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 

    return products.map((product, i) => (
        loaded ?
        < Product class='no-bullets'
            key={i+product.id}
            product={product}
        /> :
        (<td colSpan='2'>Loading...</td>)
    ));
}

export default ProductList
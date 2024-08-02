import { useState } from "react";
import { useEffect } from "react";
import { ProductCard } from "./ProductCard";
import GetProducts from "../Util/GetProducts";

//List of total products
const ProductList = ({index}) => {
    const products= GetProducts();
    
    switch(index)
    {
        case '1':
            console.log("inside 1")
            products.sort((a, b) => a.id - b.id);
            break;
        case '2':
            console.log("inside 2")
            products.sort((a, b) => a.price - b.price);
            break;
    }

    const found = products.length;
    console.log( found)
    return products.map((product, i) => (
        found>0 ?
        < ProductCard class='no-bullets'
            key={i+product.name}
            product={product}
        /> :
        (<p colSpan='2'>Found no Products</p>)
    ));
}

export default ProductList
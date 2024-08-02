import { useState } from "react";
import { useEffect } from "react";
import { ProductCard } from "./ProductCard";
import GetProducts from "../Util/GetProducts";

//List of total products
const ProductList = () => {
    const products= GetProducts();
    
    products.sort((a, b) => a.id - b.id);
    const found = products.length;
    console.log( found)
    return products.map((product, i) => (
        found>0 ?
        < ProductCard class='no-bullets'
            key={i+product.id}
            product={product}
        /> :
        (<p colSpan='2'>Found no Products</p>)
    ));
}

export default ProductList
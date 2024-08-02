import { useState } from "react";
import { useEffect } from "react";
import { ProductWarehouseCard } from "./ProductWarehouseCard";

//list of warehouse the product exists in
const ProductWarehouseList = (productInventories) => {

    console.log( productInventories.productInventory)
    return productInventories.productInventory.map((productInventory, i) => (
        < ProductWarehouseCard class='no-bullets'
            key={i+productInventory.id}
            productInventory={productInventory}
        />
    ));
}

export default ProductWarehouseList
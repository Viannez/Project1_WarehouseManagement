import { useState } from "react";
import { useEffect } from "react";
import { WarehouseProductCard } from "./WarehouseProductCard";

//List of products in warehouse
const WarehouseProductList = (productInventories) => {

    console.log( productInventories.productInventory)
    return productInventories.productInventory.map((productInventory, i) => (
        < WarehouseProductCard class='no-bullets'
            key={i+productInventory.id}
            productInventory={productInventory}
        />
    ));
}

export default WarehouseProductList
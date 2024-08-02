import { useState } from "react";
import { useEffect } from "react";
import { WarehouseCard } from "./WarehouseCard";
import GetWarehouses from "../Util/GetWarehouses";

//list of warehouse cards
const WarehouseList = () => {
    const warehouses= GetWarehouses()

    const found = warehouses.length;
    console.log( found)
    return warehouses.map((warehouse, i) => (
        found>0 ?
        < WarehouseCard class='no-bullets'
            key={i+warehouse.id}
            warehouse={warehouse}
        /> :
        (<p colSpan='2'>Found no Warehouses</p>)
    ));
}

export default WarehouseList
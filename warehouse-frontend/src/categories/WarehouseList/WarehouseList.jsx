import { useState } from "react";
import { useEffect } from "react";
import { Warehouse } from "../Warehouse/Warehouse";


const WarehouseList = () => {
    const url = "http://localhost:8080/warehouse";

    const [warehouses, setWarehouses] = useState([]);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {


        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setWarehouses(returnedData);
                setLoaded(true);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 

    return warehouses.map((warehouse, i) => (
        loaded ?
        < Warehouse class='no-bullets'
            key={i+warehouse.id}
            warehouse={warehouse}
        /> :
        (<p colSpan='2'>Loading...</p>)
    ));
}

export default WarehouseList
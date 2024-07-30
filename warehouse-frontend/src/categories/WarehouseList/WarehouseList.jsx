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
            id={warehouse.id}
            name={warehouse.name}
            address={warehouse.address}
            capacity={warehouse.capacity}
        /> :
        (<tr><td colSpan='2'>Loading...</td></tr>)
    ));
}

export default WarehouseList
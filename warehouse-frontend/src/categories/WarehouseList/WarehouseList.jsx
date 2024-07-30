import { useState } from "react";
import { useEffect } from "react";
import { Warehouse } from "../Warehouse/Warehouse";


const WarehouseList = () => {

    // TODO fetch data from backend and display in table
    // when the component is mounted

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
        // TODO instead show your own alert not builtin 
        //  might MUI snackbar etc
        //  Toast Messages


    }, []) // only fetch when mounting

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
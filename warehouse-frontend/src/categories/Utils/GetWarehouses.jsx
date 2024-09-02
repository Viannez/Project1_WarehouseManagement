import { useState } from "react";
import { useEffect } from "react";

//get all warehouses or one warehouse by id
export default function GetWarehouses(id){

    let url = "";
    if(id!=undefined){
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/warehouse/`+id;
    }
    else{
        console.log("warehouse found no id");
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/warehouse`;
    }
    console.log(url);
    const [warehouses, setWarehouses] = useState([]);

    useEffect(() => {
        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setWarehouses(returnedData);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 

    console.log( warehouses)
    return warehouses;
}
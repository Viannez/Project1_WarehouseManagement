import { useState } from "react";
import { useEffect } from "react";

//get all warehouses or one warehouse by id
export default function GetWarehouses(id){

    let url = "";
    if(id!=undefined){
        url = "http://localhost:8080/warehouse/"+id;
    }
    else{
        console.log("warehouse found no id");
        url = "http://localhost:8080/warehouse";
    }
    
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
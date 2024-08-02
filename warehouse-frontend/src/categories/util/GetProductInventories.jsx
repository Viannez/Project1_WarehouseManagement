import { useState } from "react";
import { useEffect } from "react";

export default function GetProductInventories(id){
    const [productInventories, setProductInventories] = useState([]);

    let url = "";
    if(id!=undefined){
        url = "http://localhost:8080/product_inventory/"+id;
    }
    else{
        console.log("warehouse found no id");
        url = "http://localhost:8080/product_inventory";
    }
    useEffect(() => {
        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setProductInventories(returnedData);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 
    
    console.log(productInventories)
    return productInventories;
}
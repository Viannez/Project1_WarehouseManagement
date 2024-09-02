import { useState } from "react";
import { useEffect } from "react";

//get all products or one product by id
export default function GetProductInventories(id){
    const [productInventories, setProductInventories] = useState([]);

    let url = "";
    if(id!=undefined){
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/product_inventory/`+id;
    }
    else{
        console.log("warehouse found no id");
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/product_inventory`;
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
import { useState } from "react";
import { useEffect } from "react";

//get all products or one product by id
export default function GetProducts(id){
    const [products, setProducts] = useState([]);

    let url = "";
    if(id!=undefined){
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/product/`+id;
    }
    else{
        console.log("warehouse found no id");
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/product`;
    }
    
    useEffect(() => {
        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setProducts(returnedData);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 

    console.log( products)
    return products;
}
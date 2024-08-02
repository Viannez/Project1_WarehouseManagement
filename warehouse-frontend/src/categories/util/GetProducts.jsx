import { useState } from "react";
import { useEffect } from "react";

export default function GetProducts(id){
    const [products, setProducts] = useState([]);

    let url = "";
    if(id!=undefined){
        url = "http://localhost:8080/product/"+id;
    }
    else{
        console.log("warehouse found no id");
        url = "http://localhost:8080/product";
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
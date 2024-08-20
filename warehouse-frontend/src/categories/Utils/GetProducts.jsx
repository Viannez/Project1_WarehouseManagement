import { useState } from "react";
import { useEffect } from "react";

//get all products or one product by id
export default function GetProducts(id){
    const [products, setProducts] = useState([]);

    let url = "";
    if(id!=undefined){
        url = "http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com:8080/product/"+id;
    }
    else{
        console.log("warehouse found no id");
        url = "http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com:8080/product";
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
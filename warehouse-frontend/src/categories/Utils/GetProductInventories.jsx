import { useState } from "react";
import { useEffect } from "react";

//get all products or one product by id
export default function GetProductInventories(id){
    const [productInventories, setProductInventories] = useState([]);

    let url = "";
    if(id!=undefined){
        url = `http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/product_inventory/`+id;
    }
    else{
        console.log("warehouse found no id");
        url = `http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/product_inventory`;
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
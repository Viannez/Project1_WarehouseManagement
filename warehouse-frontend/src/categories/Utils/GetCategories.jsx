import { useState } from "react";
import { useEffect } from "react";

//get all categories or one category by id
export default function GetCategories(id){
    const [categories, setCategories] = useState([]);

    let url = "";
    if(id!=undefined){
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/category/`+id;
    }
    else{
        console.log("warehouse found no id");
        url = `${import.meta.env.VITE_APP_API_ENDPOINT}/category`;
    }

    useEffect(() => {
        fetch(url)
            .then(data => data.json()) // arrow function notation rules 
            .then(returnedData => {
                setCategories(returnedData);
            })
            .catch(err => { alert(err); console.log(err) })

    }, []) 

    console.log("categories: ", categories)
    return categories;
}
import axios from "axios";

export const fetchStockByTicker = async (ticker:string) => {

    const token = localStorage.getItem("token");

    const response = await axios.get(`http://localhost:8080/ativos/${ticker}`, {
    headers:{
        Authorization: `Bearer ${token}`
    }
});


    return response.data.results[0];
}
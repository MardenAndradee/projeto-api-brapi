import axios from "axios";

const token = localStorage.getItem("token");

export const fetchStockByTicker = async (ticker:string) => {

    const response = await axios.get(`http://localhost:8080/ativos/${ticker}`, {
    headers:{
        Authorization: `Bearer ${token}`
    }
});


    return response.data.results[0];
}

export const salvarHistorico = async(idUsuario:number, ticker: string, dataConsulta: string, resultado: string) =>{

    const body = {
    usuario: {
        idUsuario: idUsuario,
    },
    ticker,
    dataConsulta,
    resultado,
  };
    
    const response = await axios.post(`http://localhost:8080/historico`, body,{
        headers:{
            Authorization: `Bearer ${token}`
        }
    });

}
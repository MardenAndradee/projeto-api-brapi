import { getToken } from "../utils/auth";
import api from "./api"

interface loginRequest{
    login: string;
    password: string;
}

interface loginResponse{
    token: string;
}

interface UsuarioResponse{
    id: number;
}

export async function loginUsuario(dados: loginRequest): Promise<string> {
    const response = await api.post<loginResponse>("/auth/login", dados);
    return response.data.token;
}

export async function ObterIdUsuarioLogado(): Promise<number> {
    try{
        const token = localStorage.getItem("token");
        if (!token) throw new Error("Token não encontrado");

        const response = await api.get<number>("usuario/me",{
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        console.log("ID: " + response.data);
        return response.data;
    }catch(error){
        throw new Error("Erro ao buscar Id do usuario autenticado")
    }
    
}
import { getToken } from "../utils/auth";
import api from "./api"

interface loginRequest{
    login: string;
    password: string;
}

interface loginResponse{
    token: string;
}

export async function loginUsuario(dados: loginRequest): Promise<string> {
    const response = await api.post<loginResponse>("/auth/login", dados);
    return response.data.token;
}
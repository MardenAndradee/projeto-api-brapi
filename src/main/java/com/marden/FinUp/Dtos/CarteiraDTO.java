package com.marden.FinUp.Dtos;

import com.marden.FinUp.Entities.Usuario;

import java.time.LocalDate;

public record CarteiraDTO (
        Usuario usuario,
        String ticker,
        double qtd,
        double precoMedio,
        double precoTotal,
        LocalDate dataCompra
){

    public Usuario getUsuario() {
        return usuario;
    }

}

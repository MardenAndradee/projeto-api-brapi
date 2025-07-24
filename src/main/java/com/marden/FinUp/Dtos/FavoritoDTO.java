package com.marden.FinUp.Dtos;

import com.marden.FinUp.Entities.Usuario;

import java.time.LocalDate;


public record FavoritoDTO(
        Usuario usuario,
        String ticker,
        String nomeAtivo,
        LocalDate dataAdicao
) {

    public Usuario getUsuario() {
        return usuario;
    }

}

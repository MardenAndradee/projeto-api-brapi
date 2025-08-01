package com.marden.FinUp.Dtos;

import com.marden.FinUp.Entities.Usuario;


import java.time.LocalDate;

public record HistoricoDTO(
        Usuario usuario,
        String ticker,
        LocalDate dataConsulta,
        String resultado
) {
    public Usuario getUsuario() {
        return usuario;
    }
}

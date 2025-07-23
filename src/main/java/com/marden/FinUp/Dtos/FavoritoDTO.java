package com.marden.FinUp.Dtos;

import java.time.LocalDate;

public record FavoritoDTO(
        String ticker,
        String nomeAtivo,
        LocalDate dataAdicao
) {
}

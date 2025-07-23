package com.marden.FinUp.Dtos;

import java.time.LocalDate;

public record HistoricoDTO(
        String ticker,
        LocalDate dataConsulta,
        String resultado
) {
}

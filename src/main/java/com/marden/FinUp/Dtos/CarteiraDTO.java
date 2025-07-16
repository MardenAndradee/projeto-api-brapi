package com.marden.FinUp.Dtos;

import java.time.LocalDate;

public record CarteiraDTO (
        Long idCarteira,
        String ticker,
        double qtd,
        double precoMedio,
        double precoTotal,
        LocalDate dataCompra


){
}

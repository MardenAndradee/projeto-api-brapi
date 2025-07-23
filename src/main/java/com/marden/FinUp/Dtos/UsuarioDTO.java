package com.marden.FinUp.Dtos;

import java.time.LocalDate;

public record UsuarioDTO(
        String nome,
        String email,
        String login,
        String senha
) {
}

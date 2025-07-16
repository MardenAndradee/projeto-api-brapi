package com.marden.FinUp.Repositories;

import com.marden.FinUp.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}

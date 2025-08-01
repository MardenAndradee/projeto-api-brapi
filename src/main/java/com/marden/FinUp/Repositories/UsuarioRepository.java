package com.marden.FinUp.Repositories;

import com.marden.FinUp.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByLogin(String login);

}

package com.marden.FinUp.Repositories;

import com.marden.FinUp.Entities.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    List<Historico> findByUsuarioIdUsuario(Long idUsuario);

}

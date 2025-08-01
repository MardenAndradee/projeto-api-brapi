package com.marden.FinUp.Services;

import com.marden.FinUp.Entities.Historico;
import com.marden.FinUp.Repositories.HistoricoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    HistoricoRepository historicoRepository;

    public Historico findById(Long id){
        Optional<Historico> historico = this.historicoRepository.findById(id);
        return historico.orElseThrow(() -> new ValidationException(
                "Historico não encontrado"
        ));
    }

    public List<Historico> getAllHistoricos(Long id){
        List<Historico> listaHistorico = historicoRepository.findByUsuarioIdUsuario(id);
        return listaHistorico;
    }

    @Transactional
    public Historico create(Historico historico){
        historico.setIdHistorico(null);
        historico = this.historicoRepository.save(historico);
        return historico;
    }

    @Transactional
    public Historico update(Historico historico){
        Historico historico1 = findById(historico.getIdHistorico());

        if(historico.getTicker() != null){
            historico1.setTicker(historico.getTicker());
        }
        if(historico.getDataConsulta() != null){
            historico1.setDataConsulta(historico.getDataConsulta());
        }
        if(historico.getResultado() != null){
            historico1.setResultado(historico.getResultado());
        }

        return this.historicoRepository.save(historico1);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            this.historicoRepository.deleteById(id);
        }catch (Exception e){
            throw new ValidationException("Historico não existe");
        }
    }

}

package com.marden.FinUp.Services;

import com.marden.FinUp.Entities.Favorito;
import com.marden.FinUp.Repositories.FavoritoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    @Autowired
    FavoritoRepository favoritoRepository;

    public Favorito findById(Long id){
        Optional<Favorito> favorito = this.favoritoRepository.findById(id);
        return favorito.orElseThrow(() -> new ValidationException(
                "Favorito não encontrado"
        ));
    }

    public List<Favorito> getAllUsuarios(Long id){
        return favoritoRepository.findAll();
    }

    @Transactional
    public Favorito create(Favorito favorito){
        favorito.setIdFavorito(null);
        favorito = this.favoritoRepository.save(favorito);
        return favorito;
    }

    @Transactional
    public Favorito update(Favorito favorito){
        Favorito favorito1 = findById(favorito.getIdFavorito());

        if(favorito.getTicker() != null){
            favorito1.setTicker(favorito.getTicker());
        }
        if(favorito.getNomeAtivo() != null){
            favorito1.setNomeAtivo(favorito.getNomeAtivo());
        }
        if(favorito.getDataAdicao() != null){
            favorito1.setDataAdicao(favorito.getDataAdicao());
        }

        return this.favoritoRepository.save(favorito1);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            this.favoritoRepository.deleteById(id);
        }catch (Exception e){
            throw new ValidationException("Favorito não existe");
        }
    }
}

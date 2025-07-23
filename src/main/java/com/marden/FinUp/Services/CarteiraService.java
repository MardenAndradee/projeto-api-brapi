package com.marden.FinUp.Services;

import com.marden.FinUp.Entities.Carteira;
import com.marden.FinUp.Repositories.CarteiraRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteiraService {

    @Autowired
    CarteiraRepository carteiraRepository;

    public Carteira findById(Long id){
        Optional<Carteira> carteira = this.carteiraRepository.findById(id);
        return carteira.orElseThrow(() -> new ValidationException(
                "Carteira não encontrado"
        ));
    }

    public List<Carteira> getAllCarteira(Long id){
        return carteiraRepository.findAll();
    }

    @Transactional
    public Carteira create(Carteira carteira){
        carteira.setIdCarteira(null);
        carteira = this.carteiraRepository.save(carteira);
        return carteira;
    }

    @Transactional
    public Carteira update(Carteira carteira){
        Carteira carteira1 = findById(carteira.getIdCarteira());

        if(carteira.getTicker() != null){
            carteira1.setTicker(carteira.getTicker());
        }
        if(carteira.getQtd() != 0){
            carteira1.setQtd(carteira.getQtd());
        }
        if(carteira.getPrecoMedio() != 0){
            carteira1.setPrecoMedio(carteira.getPrecoMedio());
        }
        if(carteira.getPrecoTotal() != 0){
            carteira1.setPrecoTotal(carteira.getPrecoTotal());
        }
        if(carteira.getDataCompra() != null){
            carteira1.setDataCompra(carteira.getDataCompra());
        }

        return this.carteiraRepository.save(carteira1);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            this.carteiraRepository.deleteById(id);
        }catch (Exception e){
            throw new ValidationException("Carteira não existe");
        }
    }

}

package com.marden.FinUp.Controllers;

import com.marden.FinUp.Dtos.CarteiraDTO;
import com.marden.FinUp.Entities.Carteira;
import com.marden.FinUp.Entities.Usuario;
import com.marden.FinUp.Services.CarteiraService;
import com.marden.FinUp.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarteiraController {

    @Autowired
    CarteiraService carteiraService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/carteira")
    public ResponseEntity<Carteira> saveCarteira(@RequestBody @Valid CarteiraDTO carteiraDTO){
        var carteira = new Carteira();
        BeanUtils.copyProperties(carteiraDTO, carteira);

        Usuario usuario = usuarioService.findById(carteiraDTO.getUsuario().getIdUsuario());
        carteira.setIdUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(carteiraService.create(carteira));
    }

    @GetMapping("/carteira")
    public ResponseEntity<List<Carteira>> getAllCarteira(@RequestParam(required = false) Long idUsuario){


        return ResponseEntity.status(HttpStatus.OK).body(carteiraService.getAllCarteira(idUsuario));
    }

    @GetMapping("/carteira/{id}")
    public ResponseEntity<Object> getOneCarteira(@PathVariable(value="id")long id){
        Carteira carteira = this.carteiraService.findById(id);
        return ResponseEntity.ok().body(carteira);
    }

    @PutMapping("/carteira/{id}")
    public ResponseEntity<Object> updateCarteira(@Valid @RequestBody Carteira carteira, @PathVariable Long id){
        carteira.setIdCarteira(id);
        this.carteiraService.update(carteira);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/carteira/{id}")
    public ResponseEntity<Object> deleteCarteira(@PathVariable(value = "id") Long id){
        this.carteiraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package com.marden.FinUp.Controllers;

import com.marden.FinUp.Dtos.FavoritoDTO;
import com.marden.FinUp.Entities.Favorito;
import com.marden.FinUp.Entities.Usuario;
import com.marden.FinUp.Services.FavoritoService;
import com.marden.FinUp.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoritoController {

    @Autowired
    FavoritoService favoritoService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/favorito")
    public ResponseEntity<Favorito> saveFavorito(@RequestBody @Valid FavoritoDTO favoritoDTO){
        var favorito = new Favorito();
        BeanUtils.copyProperties(favoritoDTO, favorito);

        Usuario usuario = usuarioService.findById(favoritoDTO.getUsuario().getIdUsuario());
        favorito.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.create(favorito));
    }

    @GetMapping("/favorito")
    public ResponseEntity<List<Favorito>> getAllFavorito(@RequestParam(required = false) Long idUsuario){


        return ResponseEntity.status(HttpStatus.OK).body(favoritoService.getAllFavoritos(idUsuario));
    }

    @GetMapping("/favorito/{id}")
    public ResponseEntity<Object> getOneFavorito(@PathVariable(value="id")long id){
        Favorito favorito = this.favoritoService.findById(id);
        return ResponseEntity.ok().body(favorito);
    }

    @PutMapping("/favorito/{id}")
    public ResponseEntity<Object> updateFavorito(@Valid @RequestBody Favorito favorito, @PathVariable Long id){
        favorito.setIdFavorito(id);
        this.favoritoService.update(favorito);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/favorito/{id}")
    public ResponseEntity<Object> deleteFavorito(@PathVariable(value = "id") Long id){
        this.favoritoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

package com.marden.FinUp.Controllers;

import com.marden.FinUp.Dtos.HistoricoDTO;
import com.marden.FinUp.Entities.Historico;
import com.marden.FinUp.Entities.Usuario;
import com.marden.FinUp.Services.HistoricoService;
import com.marden.FinUp.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HistoricoController {

    @Autowired
    HistoricoService historicoService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/historico")
    public ResponseEntity<Historico> saveHistorico(@RequestBody @Valid HistoricoDTO historicoDTO){
        var historico = new Historico();
        BeanUtils.copyProperties(historicoDTO, historico);

        Usuario usuario = usuarioService.findById(historicoDTO.getUsuario().getIdUsuario());
        historico.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(historicoService.create(historico));
    }

    @GetMapping("/historico")
    public ResponseEntity<List<Historico>> getAllHistorico(@RequestParam(required = false) Long idUsuario){


        return ResponseEntity.status(HttpStatus.OK).body(historicoService.getAllHistoricos(idUsuario));
    }

    @GetMapping("/historico/{id}")
    public ResponseEntity<Object> getOneHistorico(@PathVariable(value="id")long id){
        Historico historico = this.historicoService.findById(id);
        return ResponseEntity.ok().body(historico);
    }

    @PutMapping("/historico/{id}")
    public ResponseEntity<Object> updateHistorico(@Valid @RequestBody Historico historico, @PathVariable Long id){
        historico.setIdHistorico(id);
        this.historicoService.update(historico);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/historico/{id}")
    public ResponseEntity<Object> deleteHistorico(@PathVariable(value = "id") Long id){
        this.historicoService.delete(id);
        return ResponseEntity.noContent().build();
    }



}

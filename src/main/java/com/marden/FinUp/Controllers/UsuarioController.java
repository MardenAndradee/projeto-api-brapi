package com.marden.FinUp.Controllers;

import com.marden.FinUp.Dtos.UsuarioDTO;
import com.marden.FinUp.Entities.Usuario;
import com.marden.FinUp.Repositories.UsuarioRepository;
import com.marden.FinUp.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveUsuarios(@RequestBody @Valid UsuarioDTO usuariosDTO){
        var usuarios = new Usuario();
        BeanUtils.copyProperties(usuariosDTO, usuarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.create(usuarios));
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAllUsuarios());
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario usuarios = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(usuarios);


    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody Usuario user, @PathVariable Long id){
        user.setIdUsuario(id);
        this.usuarioService.update(user);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/me")
    public ResponseEntity<Long> getIdUsuarioLogado(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = usuarioRepository.findByLogin(login);

        if(usuario!=null){
            return ResponseEntity.ok(usuario.getIdUsuario());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}

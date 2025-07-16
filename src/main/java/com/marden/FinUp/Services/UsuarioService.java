package com.marden.FinUp.Services;

import com.marden.FinUp.Entities.Usuario;
import com.marden.FinUp.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario findById(Long id){
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ValidationException(
                "Usuario não encontrado"
        ));
    }

    public List<Usuario> getAllUsuarios(Long id){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario create(Usuario user){
        user.setIdUsuario(null);
        user = this.usuarioRepository.save(user);
        return user;
    }

    @Transactional
    public Usuario update(Usuario user){
        Usuario usuario = findById(user.getIdUsuario());

        if(user.getEmail() != null){
            usuario.setEmail(user.getEmail());
        }
        if(user.getLogin() != null){
            usuario.setLogin(user.getLogin());
        }
        if(user.getNome() != null){
            usuario.setNome(user.getNome());
        }
        if(user.getSenha() != null){
            usuario.setSenha(user.getSenha());
        }

        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            this.usuarioRepository.deleteById(id);
        }catch (Exception e){
            throw new ValidationException("Usuário não existe");
        }
    }

}

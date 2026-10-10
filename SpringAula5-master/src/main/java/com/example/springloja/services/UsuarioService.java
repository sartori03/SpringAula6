package com.example.springloja.services;

import com.example.springloja.dto.UsuarioDTO;
import com.example.springloja.entities.Usuario;
import com.example.springloja.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll().stream().map(UsuarioDTO::new).toList();
    }

    public void salvar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }

    public void atualizar(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }
}



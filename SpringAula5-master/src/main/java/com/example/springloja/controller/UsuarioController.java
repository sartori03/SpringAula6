package com.example.springloja.controller;

import com.example.springloja.dto.UsuarioDTO;
import com.example.springloja.entities.Usuario;
import com.example.springloja.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public String salvar(@Valid @RequestBody Usuario usuario) {
        service.salvar(usuario);
        return "Usuario salvo com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,@Valid @RequestBody Usuario usuario){
        usuario.setId(id);
        service.atualizar(usuario);
        return "Usuario atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        service.excluir(id);
        return "Usuario deletado com sucesso!";
    }
}

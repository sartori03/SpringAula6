package com.example.springloja.dto;

import com.example.springloja.entities.Usuario;

public record UsuarioDTO(
        Long id,
        String username,
        String role
) {
    public UsuarioDTO(Usuario u){
        this(u.getId(), u.getUsername(), u.getRole());
    };
}


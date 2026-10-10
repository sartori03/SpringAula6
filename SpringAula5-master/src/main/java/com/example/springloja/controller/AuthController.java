package com.example.springloja.controller;

import com.example.springloja.entities.Usuario;
import com.example.springloja.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid Usuario usuario) {
        // 1. Cria o objeto com as credenciais não autenticadas
        var tokenAutenticacao = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());

        // 2. O AuthenticationManager valida o usuário e a senha (com BCrypt)
        var authentication = authenticationManager.authenticate(tokenAutenticacao);

        // 3. Se passou pela linha acima, a senha está correta! Gera o JWT.
        String tokenJwt = tokenService.gerarToken(authentication.getName());

        return ResponseEntity.ok(Map.of("token", tokenJwt));
    }
}



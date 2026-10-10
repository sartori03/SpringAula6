package com.example.springloja.config;

import com.example.springloja.entities.Usuario;
import com.example.springloja.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargaDadosConfig {

    @Bean
    public CommandLineRunner carregarUsuarios(UsuarioService usuarioService) {
        return args -> {
            // Usa o service para consultar e para salvar
            if (usuarioService.listarTodos().isEmpty()) {
                Usuario admin = new Usuario("admin", "123456", "ROLE_ADMIN");

                // O próprio UsuarioService já vai criptografar a senha via BCrypt e salvar
                usuarioService.salvar(admin);

                System.out.println(">>> Usuário admin criado com sucesso no banco SQLite! <<<");
            }
        };
    }
}
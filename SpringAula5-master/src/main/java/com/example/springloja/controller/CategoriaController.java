package com.example.springloja.controller;

import com.example.springloja.entities.Categoria;
import com.example.springloja.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping
    public List<Categoria> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public String salvar(@Valid @RequestBody Categoria Categoria) {
        service.salvar(Categoria);
        return "Categoria salva com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,@Valid @RequestBody Categoria Categoria){
        Categoria.setId(id);
        service.atualizar(Categoria);
        return "Categoria atualizada com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        service.excluir(id);
        return "Categoria deletada com sucesso!";
    }

    @GetMapping("/filtro-nome")
    public List<Categoria> filtrarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

}

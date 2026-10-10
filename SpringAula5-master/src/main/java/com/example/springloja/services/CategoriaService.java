package com.example.springloja.services;

import com.example.springloja.entities.Categoria;
import com.example.springloja.entities.Produto;
import com.example.springloja.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public void salvar(Categoria Categoria) {
        repository.save(Categoria);
    }

    public void atualizar(Categoria categoria){
        repository.save(categoria);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public List<Categoria> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

}



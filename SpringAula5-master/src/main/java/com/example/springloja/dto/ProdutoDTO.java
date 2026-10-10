package com.example.springloja.dto;

import com.example.springloja.entities.Produto;

public record ProdutoDTO(
        Long id,
        String nome,
        Double preco,
        String foto,
        String manual,
        String nomeCategoria

) {
    public ProdutoDTO(Produto p){
        this(p.getId(), p.getNome(), p.getPreco(), p.getFoto(), p.getManual(), p.getCategoria().getNome());
    }
}


package com.example.springloja.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do produto gerado pelo banco", example = "1")
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Schema(description = "Nome do produto", example = "Teclado")
    private String nome;

    @Min(value = 0, message = "O preço não pode ser negativo")
    @Schema(description = "Preço unitário do produto", example = "250.00")
    private Double preco;

    @NotNull(message = "A categoria é obrigatória")
    @Schema(description = "Categoria do produto", example = "{ \"id\": 1 }")
    @ManyToOne
    private Categoria categoria;

    @Schema(description = "Foto do produto", example = "imagem.jpg")
    private String foto;

    @Schema(description = "Manual em PDF do produto", example = "manual.pdf")
    private String manual;

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }
}

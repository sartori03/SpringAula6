package com.example.springloja.controller;

import com.example.springloja.dto.ProdutoDTO;
import com.example.springloja.entities.Produto;
import com.example.springloja.services.ProdutoService;
import com.example.springloja.services.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
@Tag(name="Produtos", description = "Gerenciamento de estoque de produtos")
public class ProdutoController {
    @Autowired
    private StorageService storageService;

    @Autowired
    private ProdutoService service;

    @GetMapping
    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista completa de produtos com suas respectivas categorias")
    public List<ProdutoDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearerAuth")
    public String criar(@Valid @RequestPart("produto") Produto produto,
                        @RequestPart(value = "foto", required = false)MultipartFile foto,
                        @RequestPart(value = "manual", required = false)MultipartFile manual){
        if(foto != null && !foto.isEmpty()) {
            String caminho = storageService.salvarArquivo(foto);
            produto.setFoto(caminho);
        }
        if(manual != null && !manual.isEmpty()) {
            String caminho = storageService.salvarArquivo(manual);
            produto.setManual(caminho);
        }
        service.salvar(produto);
        return "Produto " + produto.getNome() + " criado com sucesso!";
    }

    @PutMapping(value ="/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearerAuth")
    public String atualizar(@PathVariable Long id, @Valid @RequestPart Produto produto,
                            @RequestPart(value = "foto", required = false) MultipartFile foto,
                            @RequestPart(value = "manual", required = false) MultipartFile manual){
        produto.setId(id);
        service.atualizar(produto, foto, manual);
        return "Produto atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        service.excluir(id);
        return "Produto deletado com sucesso!";
    }

    @GetMapping("/filtro-nome")
    public List<ProdutoDTO> filtrarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/filtro-preco")
    public List<ProdutoDTO> filtrarPorPreco(@RequestParam(name="valor") Double preco) {
        return service.buscarPorPrecoMaximo(preco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id){
        ProdutoDTO obj = service.buscarPorId(id);

        if (obj != null){
            return ResponseEntity.ok().body(obj);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}

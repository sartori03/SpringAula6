package com.example.springloja.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageService {
    private final Path diretorioRaiz = Paths.get("uploads");
    public String salvarArquivo(MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            return null;
        }
        try {
            if (!Files.exists(diretorioRaiz)) {
                    Files.createDirectories(diretorioRaiz);
            }
            String nomeOriginal = arquivo.getOriginalFilename();
            String extensao = "";
            if (nomeOriginal != null && nomeOriginal.contains(".")) {
                extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
            }
            String nomeArquivoUnico = UUID.randomUUID().toString() + extensao;
            Path destinoFinal = diretorioRaiz.resolve(nomeArquivoUnico);

            Files.copy(arquivo.getInputStream(), destinoFinal, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + nomeArquivoUnico;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }
    }

    public void deletarArquivo(String caminho) {
        if (caminho == null || caminho.isBlank()) {
            return;
        }

        try {
            String caminhoRelativo =
                        caminho.startsWith("/") ? caminho.substring(1) : caminho;
            Path arquivo = Paths.get(caminhoRelativo);
            Files.deleteIfExists(arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao deletar arquivo antigo: " + e.getMessage());
        }
    }
}

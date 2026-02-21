package com.java.projetc.controller;

import com.java.projetc.dto.ProdutoDTO;
import com.java.projetc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // POST - Criar novo produto
    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoCriado = produtoService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    // GET - Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    // GET - Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        Optional<ProdutoDTO> produto = produtoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET - Buscar por categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<ProdutoDTO> produtos = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    // GET - Buscar por marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProdutoDTO>> buscarPorMarca(@PathVariable String marca) {
        List<ProdutoDTO> produtos = produtoService.buscarPorMarca(marca);
        return ResponseEntity.ok(produtos);
    }

    // PUT - Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoDTO> produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);
        return produtoAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoService.deletarProduto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


package com.java.projetc.service;

import com.java.projetc.dto.ProdutoDTO;
import com.java.projetc.model.Produto;
import com.java.projetc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // CREATE - Criar novo produto
    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setEstoque(produtoDTO.getEstoque());
        produto.setCategoria(produtoDTO.getCategoria());
        produto.setMarca(produtoDTO.getMarca());

        Produto produtoSalvo = produtoRepository.save(produto);
        return converterParaDTO(produtoSalvo);
    }

    // READ - Listar todos os produtos
    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // READ - Buscar produto por ID
    public Optional<ProdutoDTO> buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(this::converterParaDTO);
    }

    // READ - Buscar por categoria
    public List<ProdutoDTO> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // READ - Buscar por marca
    public List<ProdutoDTO> buscarPorMarca(String marca) {
        return produtoRepository.findByMarca(marca)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // UPDATE - Atualizar produto
    public Optional<ProdutoDTO> atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            if (produtoDTO.getNome() != null) produto.setNome(produtoDTO.getNome());
            if (produtoDTO.getDescricao() != null) produto.setDescricao(produtoDTO.getDescricao());
            if (produtoDTO.getPreco() != null) produto.setPreco(produtoDTO.getPreco());
            if (produtoDTO.getEstoque() != null) produto.setEstoque(produtoDTO.getEstoque());
            if (produtoDTO.getCategoria() != null) produto.setCategoria(produtoDTO.getCategoria());
            if (produtoDTO.getMarca() != null) produto.setMarca(produtoDTO.getMarca());

            Produto produtoAtualizado = produtoRepository.save(produto);
            return Optional.of(converterParaDTO(produtoAtualizado));
        }

        return Optional.empty();
    }

    // DELETE - Deletar produto
    public boolean deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Converter Produto para ProdutoDTO
    private ProdutoDTO converterParaDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getCategoria(),
                produto.getMarca()
        );
    }
}


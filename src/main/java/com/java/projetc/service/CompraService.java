package com.java.projetc.service;

import com.java.projetc.dto.CompraDTO;
import com.java.projetc.model.Compra;
import com.java.projetc.model.Produto;
import com.java.projetc.repository.CompraRepository;
import com.java.projetc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraService {

    @Autowired
    @SuppressWarnings("all")
    private CompraRepository compraRepository;

    @Autowired
    @SuppressWarnings("all")
    private ProdutoRepository produtoRepository;

    // CREATE - Realizar nova compra
    public Optional<CompraDTO> realizarCompra(CompraDTO compraDTO) {
        Optional<Produto> produtoOptional = produtoRepository.findById(compraDTO.getProdutoId());

        if (produtoOptional.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = produtoOptional.get();

        // Validar estoque
        if (produto.getEstoque() < compraDTO.getQuantidade()) {
            return Optional.empty();
        }

        // Criar compra
        Compra compra = new Compra();
        compra.setCliente(compraDTO.getCliente());
        compra.setEmail(compraDTO.getEmail());
        compra.setTelefone(compraDTO.getTelefone());
        compra.setProduto(produto);
        compra.setQuantidade(compraDTO.getQuantidade());
        compra.setValorTotal(produto.getPreco().multiply(new BigDecimal(compraDTO.getQuantidade())));
        compra.setStatus("CONFIRMADA");

        // Reduzir estoque
        produto.setEstoque(produto.getEstoque() - compraDTO.getQuantidade());
        produtoRepository.save(produto);

        // Salvar compra
        Compra compraSalva = compraRepository.save(compra);

        return Optional.of(converterParaDTO(compraSalva));
    }

    // READ - Listar todas as compras
    public List<CompraDTO> listarTodas() {
        return compraRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // READ - Buscar compra por ID
    public Optional<CompraDTO> buscarPorId(Long id) {
        return compraRepository.findById(id)
                .map(this::converterParaDTO);
    }

    // READ - Buscar compras por cliente
    public List<CompraDTO> buscarPorCliente(String cliente) {
        return compraRepository.findByCliente(cliente)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // READ - Buscar compras por status
    public List<CompraDTO> buscarPorStatus(String status) {
        return compraRepository.findByStatus(status)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // UPDATE - Cancelar compra
    public Optional<CompraDTO> cancelarCompra(Long id) {
        Optional<Compra> compraOptional = compraRepository.findById(id);

        if (compraOptional.isPresent()) {
            Compra compra = compraOptional.get();

            if ("CANCELADA".equals(compra.getStatus())) {
                return Optional.of(converterParaDTO(compra));
            }

            // Restaurar estoque
            Produto produto = compra.getProduto();
            produto.setEstoque(produto.getEstoque() + compra.getQuantidade());
            produtoRepository.save(produto);

            // Cancelar compra
            compra.setStatus("CANCELADA");
            Compra compraAtualizada = compraRepository.save(compra);

            return Optional.of(converterParaDTO(compraAtualizada));
        }

        return Optional.empty();
    }

    // DELETE - Deletar compra
    public boolean deletarCompra(Long id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Converter Compra para CompraDTO
    private CompraDTO converterParaDTO(Compra compra) {
        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setCliente(compra.getCliente());
        dto.setEmail(compra.getEmail());
        dto.setTelefone(compra.getTelefone());
        dto.setProdutoId(compra.getProduto().getId());
        dto.setProdutoNome(compra.getProduto().getNome());
        dto.setQuantidade(compra.getQuantidade());
        dto.setValorTotal(compra.getValorTotal());
        dto.setDataCompra(compra.getDataCompra().toString());
        dto.setStatus(compra.getStatus());
        return dto;
    }
}


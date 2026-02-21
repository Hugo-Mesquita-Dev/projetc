package com.java.projetc.controller;

import com.java.projetc.dto.CompraDTO;
import com.java.projetc.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompraController {

    @Autowired
    private CompraService compraService;

    // POST - Realizar nova compra
    @PostMapping
    public ResponseEntity<CompraDTO> realizarCompra(@RequestBody CompraDTO compraDTO) {
        Optional<CompraDTO> compra = compraService.realizarCompra(compraDTO);

        if (compra.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(compra.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // GET - Listar todas as compras
    @GetMapping
    public ResponseEntity<List<CompraDTO>> listarTodas() {
        List<CompraDTO> compras = compraService.listarTodas();
        return ResponseEntity.ok(compras);
    }

    // GET - Buscar compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> buscarPorId(@PathVariable Long id) {
        Optional<CompraDTO> compra = compraService.buscarPorId(id);
        return compra.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET - Buscar compras por cliente
    @GetMapping("/cliente/{cliente}")
    public ResponseEntity<List<CompraDTO>> buscarPorCliente(@PathVariable String cliente) {
        List<CompraDTO> compras = compraService.buscarPorCliente(cliente);
        return ResponseEntity.ok(compras);
    }

    // GET - Buscar compras por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CompraDTO>> buscarPorStatus(@PathVariable String status) {
        List<CompraDTO> compras = compraService.buscarPorStatus(status);
        return ResponseEntity.ok(compras);
    }

    // PUT - Cancelar compra
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<CompraDTO> cancelarCompra(@PathVariable Long id) {
        Optional<CompraDTO> compra = compraService.cancelarCompra(id);
        return compra.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Deletar compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        if (compraService.deletarCompra(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


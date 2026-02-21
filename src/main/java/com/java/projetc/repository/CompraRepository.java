package com.java.projetc.repository;

import com.java.projetc.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByCliente(String cliente);
    List<Compra> findByStatus(String status);
    List<Compra> findByDataCompraBetween(LocalDateTime inicio, LocalDateTime fim);
}


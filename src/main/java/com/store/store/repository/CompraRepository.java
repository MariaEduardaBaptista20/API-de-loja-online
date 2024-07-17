package com.store.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.store.entity.Compra;
import com.store.store.entity.Favorito;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
	 List<Compra> findByUsuarioId(Long usuarioId);
}
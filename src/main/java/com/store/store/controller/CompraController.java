package com.store.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.entity.Compra;
import com.store.store.entity.Produto;
import com.store.store.entity.Usuario;
import com.store.store.service.CompraService;
import com.store.store.service.UsuarioService;

@RestController
@RequestMapping("/compra")
public class CompraController {

	

	@Autowired
	CompraService compraService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<Compra>> listar(@PathVariable Long id, Pageable pageable){
		return ResponseEntity.ok(compraService.listarComprasPorUsuario(id, pageable));
	}
	
	
	 @PostMapping("/{id}")
	    public ResponseEntity<Compra> adicionarNaListaDeDesejos(@PathVariable Long id, @RequestBody List<Long> listaId) {
		 Compra compra = compraService.criarCompra(id, listaId);
		  return ResponseEntity.ok().body(compra);
	    }
	
	
}
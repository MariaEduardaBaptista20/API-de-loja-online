package com.store.store.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.store.store.dto.ProdutoDTO;
import com.store.store.entity.Produto;
import com.store.store.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

		
		@Autowired
		ProdutoService produtoService;
		
		@GetMapping
		public ResponseEntity<List<ProdutoDTO>> listar(){
			return ResponseEntity.ok(produtoService.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id){
			
			return ResponseEntity.ok(produtoService.buscar(id));
		}
		
		
		@PostMapping
		public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
			produto = produtoService.cadastrar(produto);
			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(produto.getId())
					.toUri();
			
			return ResponseEntity.created(uri).body(produto);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Produto> atualizar(@RequestBody Produto produto, @PathVariable Long id){
			produto = produtoService.atualizar(produto, id);
			
			return ResponseEntity.ok().body(produto);
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> remover(@PathVariable Long id){
			produtoService.remover(id);
			return ResponseEntity.noContent().build();
		}
	

}

package com.store.store.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.store.store.entity.Produto;
import com.store.store.entity.Usuario;
import com.store.store.service.UsuarioService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

		
		@Autowired
		UsuarioService usuarioService;
		
		@GetMapping
		public ResponseEntity<Page<Usuario>> listar(Pageable pageable){
			return ResponseEntity.ok(usuarioService.listar(pageable));
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Usuario> buscar(@PathVariable Long id){
			Optional<Usuario> usuarioOpt = usuarioService.buscar(id);
			if (usuarioOpt.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(usuarioOpt.get());
		}
		
		
		@PostMapping
		public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
			usuario = usuarioService.cadastrar(usuario);
			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(usuario.getId())
					.toUri();
			
			return ResponseEntity.created(uri).body(usuario);
		}
		
		  @PostMapping("/{id}/listadesejos")
		    public ResponseEntity<Usuario> adicionarNaListaDeDesejos(@PathVariable Long id, @RequestBody Produto produto) {
			 Usuario usuario = usuarioService.adicionarNaListaDeDesejos(id, produto);
			  return ResponseEntity.ok().body(usuario);
		    }
		
		@PutMapping("/{id}")
		public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario, @PathVariable Long id){
			usuario = usuarioService.atualizar(usuario, id);
			
			return ResponseEntity.ok().body(usuario);
		}
		
		
		@DeleteMapping("/{id}/listadesejos")
		@Transactional
		public ResponseEntity<Usuario> removerDaListaDeDesejos(@PathVariable Long id, @RequestBody Produto produto){
			Usuario usuario = usuarioService.RemoverDaListaDeDesejos(id, produto);
		
			 return ResponseEntity.ok().body(usuario);
		}


		@DeleteMapping("/{id}")
		public ResponseEntity<Void> remover(@PathVariable Long id){
			usuarioService.remover(id);
			return ResponseEntity.noContent().build();
		}
	

}

package com.store.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.store.store.dto.ProdutoDTO;
import com.store.store.entity.Compra;
import com.store.store.entity.Produto;
import com.store.store.entity.Usuario;
import com.store.store.exception.ProdutoException;
import com.store.store.exception.UsuarioException;
import com.store.store.repository.CompraRepository;
import com.store.store.repository.ProdutoRepository;
import com.store.store.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CompraService {

	    @Autowired
	    private CompraRepository compraRepository;
	    
	    @Autowired
	    private UsuarioRepository usuarioRepository;
	    
	    @Autowired
	    private ProdutoRepository produtoRepository;
	    
	    
	    public Compra criarCompra(Long usuarioId, List<Long> listaId) {
	    	Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

			if (usuarioOpt == null) {
				throw new UsuarioException("Usuario não existe");
			}
	    
			  Usuario usuario = usuarioOpt.get();
			  
			  List<Produto> listaProdutos = listaId.stream().map(id -> {
				 Optional<Produto> produtoOpt = produtoRepository.findById(id);
				 if (produtoOpt == null) {
						throw new ProdutoException("Produto não existe");
					}
				 Produto produto = produtoOpt.get();
				 return produto;
				  }).collect(Collectors.toList());
			  
			  
	        List<Produto> produtos = listaProdutos.stream().map(produto -> { 
	        	if(produto.getEstoque() < 1) {
	        		   throw new IllegalStateException("Quantidade solicitada não disponível em estoque.");
	        	}else {
	        		produto.setEstoque(produto.getEstoque() - 1);
	        		produtoRepository.save(produto);
	        	
	        	}
	        	
	        	return produto;
	        }).collect(Collectors.toList());
	  
	        
	        Compra compra = new Compra();
	        compra.setUsuario(usuario);
	        compra.setPedido(produtos);
	       
	        compra = compraRepository.save(compra);
	        return compra;
	       
	      
	    }
	    
	    public Page<Compra> listarComprasPorUsuario(Long usuarioId, Pageable pageable) {
	    	Page<Compra> comprasDoUsuario = compraRepository.findByUsuarioId(usuarioId, pageable);
	    	return comprasDoUsuario;
	    }
}
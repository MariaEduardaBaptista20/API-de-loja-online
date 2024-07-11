package com.store.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.store.dto.ProdutoDTO;
import com.store.store.entity.Produto;
import com.store.store.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoDTO> findAll(){
		List<Produto>produtos = produtoRepository.findAll();

		List<ProdutoDTO> produtosDTO = produtos.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());
		return produtosDTO;
	}
	
	public Optional<Produto>findById(Long id){
		return produtoRepository.findById(id);
	}
	
	public Produto cadastrar(Produto produto) {
		produto = produtoRepository.save(produto);
		return produto;
	}


}

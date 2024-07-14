package com.store.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.store.dto.ProdutoDTO;
import com.store.store.entity.Produto;
import com.store.store.exception.ProdutoException;
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
	
	public ProdutoDTO buscar(Long id){
		Optional<Produto> produtoOpt = produtoRepository.findById(id);

		if (produtoOpt == null) {
			throw new ProdutoException("Produto não existe");
		}
		Produto produto = produtoOpt.get();
		ProdutoDTO produtoDTO = new ProdutoDTO(produto);
		return produtoDTO;
		
	}
	
	public Produto cadastrar(Produto produto) {
		produto = produtoRepository.save(produto);
		return produto;
	}
	
	public Produto atualizar(Produto produto, Long id) {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);

		if (produtoOpt == null) {
			throw new ProdutoException("Produto não existe");
		}
		produto.setId(id);
		produto = produtoRepository.save(produto);
		return (produto);
	}
	

	public Optional<Produto> remover(Long id) {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		if (produtoOpt.isEmpty()) {
			throw new ProdutoException("Produto não existe");
		}
		produtoRepository.deleteById(id);
		return produtoRepository.findById(id);
	}

}

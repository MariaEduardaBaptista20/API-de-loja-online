package com.store.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.store.store.dto.ProdutoDTO;
import com.store.store.entity.Produto;
import com.store.store.exception.ProdutoException;
import com.store.store.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Page<ProdutoDTO> listar(@PageableDefault(size= 10) Pageable pageable){
		Page<Produto>produtos = produtoRepository.findAll(pageable);

		
		return produtos.map(ProdutoDTO::new);
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

package com.store.store.dto;

import com.store.store.entity.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProdutoDTO {

		@Id
		@Column(name = "id_produto")
		private Long id;
		
		@Column(name = "nome", length = 60)
		private String nome;
		
		@Column(name = "categoria", length = 60)
		private String categoria;
		
		@Column(name = "imagem")
		private String imagem;
		
		@Column(name = "descricao")
		private String descricao;
		
		@Column(name = "valor")
		private Integer valor;

	

		public ProdutoDTO(Long id, String nome, String categoria, String imagem, String descricao, Integer valor) {
			this.id = id;
			this.nome = nome;
			this.categoria = categoria;
			this.imagem = imagem;
			this.descricao = descricao;
			this.valor = valor;
		}

		public ProdutoDTO(Produto produto) {
			this.id = produto.getId();
			this.nome = produto.getNome();
			this.categoria = produto.getCategoria();
			this.imagem = produto.getImagem();
			this.descricao = produto.getDescricao();
			this.valor = produto.getValor();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public String getImagem() {
			return imagem;
		}

		public void setImagem(String imagem) {
			this.imagem = imagem;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public Integer getValor() {
			return valor;
		}

		public void setValor(Integer valor) {
			this.valor = valor;
		}
		
		




		
	}



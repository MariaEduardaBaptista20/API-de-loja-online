package com.store.store.entity;

import java.util.Objects;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;
	
	@Column(name = "nome", length = 60)
	private String nome;
	
	@Column(name = "categoria", length = 60)
	private String categoria;
	
	@Column(name = "imagem")
	private String imagem;
	
	@Column(name = "descricão")
	private String descricao;
	
	@Column(name = "valor")
	private Integer valor;
	
	@Column(name = "estoque")
	private Long estoque;
	
	
	public Produto() {}

	public Produto(Long id, String nome, String categoria, String imagem, String descricao, Integer valor, Long estoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.imagem = imagem;
		this.descricao = descricao;
		this.valor = valor;
		this.estoque = estoque;
	}

	public Long getEstoque() {
		return estoque;
	}

	public void setEstoque(Long estoque) {
		this.estoque = estoque;
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

	@Override
	public int hashCode() {
		return Objects.hash(categoria, descricao, estoque, id, imagem, nome, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(estoque, other.estoque) && Objects.equals(id, other.id)
				&& Objects.equals(imagem, other.imagem) && Objects.equals(nome, other.nome)
				&& Objects.equals(valor, other.valor);
	}
	




	
}

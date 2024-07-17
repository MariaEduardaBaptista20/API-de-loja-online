package com.store.store.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Compra {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "compra_produto",
	joinColumns = @JoinColumn(name = "compra_id"),
	inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> pedido;
    
    public Compra() {}

	public Compra( Usuario usuario, List<Produto> pedido) {
		this.usuario = usuario;
		this.pedido = pedido;
	}
	
	

	public Compra(Long id, Usuario usuario, List<Produto> pedido) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonIgnore
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Produto> getPedido() {
		return pedido;
	}

	public void setPedido(List<Produto> pedido) {
		this.pedido = pedido;
	}
    
}
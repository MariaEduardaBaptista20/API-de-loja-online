package com.store.store.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Favorito {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "usuario_id")
	    private Usuario usuario;

	    
	    @ManyToOne    
	    @JoinColumn(name = "produto_id")
	    private Produto produto;

		public Favorito(Usuario usuario, Produto produto) {
			this.usuario = usuario;
			this.produto = produto;
		}
		
		public Favorito() {}

		@JsonIgnore
		public Long getId() {
			return id;
		}

		@JsonIgnore
		public void setId(Long id) {
			this.id = id;
		}

		@JsonIgnore
		public Usuario getUsuario() {
			return usuario;
		}

		@JsonIgnore
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, produto, usuario);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Favorito other = (Favorito) obj;
			return Objects.equals(id, other.id) && Objects.equals(produto, other.produto)
					&& Objects.equals(usuario, other.usuario);
		}
	    
	    
}

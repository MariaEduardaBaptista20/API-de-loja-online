package com.store.store.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_usuario")
		private Long id;
		
		@Column(name = "nome", length = 60)
		private String nome;
		
		@Column(name = "senha", length = 60)
		private String senha;
		
		@Column(name = "imagem")
		private String imagem;
		
		@Column(name = "idade")
		private Integer idade;
		
		  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
		    private List<Favorito> favoritos;
		    
		    // getters e setters para favoritos
		    public List<Favorito> getFavoritos() {
		        return favoritos;
		    }

		    public void setFavoritos(List<Favorito> favoritos) {
		        this.favoritos = favoritos;
		    }
		  
		public Usuario() {}
		
		public Usuario(Long id, String nome, String senha, String imagem, String descricao, Integer idade) {
			super();
			this.id = id;
			this.nome = nome;
			this.senha = senha;
			this.imagem = imagem;
			this.idade = idade;
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

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getImagem() {
			return imagem;
		}

		public void setImagem(String imagem) {
			this.imagem = imagem;
		}
		public Integer getIdade() {
			return idade;
		}

		public void setIdade(Integer idade) {
			this.idade = idade;
		}
		


		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Usuario other = (Usuario) obj;
			return Objects.equals(id, other.id);
		}

		

	

		
}

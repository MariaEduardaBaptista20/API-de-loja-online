package com.store.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.store.store.entity.Favorito;
import com.store.store.entity.Produto;
import com.store.store.entity.Usuario;
import com.store.store.exception.ProdutoException;
import com.store.store.exception.UsuarioException;
import com.store.store.repository.FavoritoRepository;
import com.store.store.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FavoritoRepository favoritoRepository;
	
	
	public Page<Usuario> listar(Pageable pageable){
		Page<Usuario>usuario = usuarioRepository.findAll(pageable);

		
		return usuario;
	}
	
	public Optional<Usuario>buscar(Long id){
		return usuarioRepository.findById(id);
	}
	
	public Usuario cadastrar(Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario atualizar(Usuario usuario, Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

		if (usuarioOpt == null) {
			throw new UsuarioException("Usuario não existe");
		}
		usuario.setId(id);
		usuario = usuarioRepository.save(usuario);
		return (usuario);
	}
	
	  public Usuario adicionarNaListaDeDesejos(Long id, Produto produto) {
	        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
	        if (usuarioOpt == null) {
				throw new UsuarioException("Usuario não existe");
			}
	        
	    
	        Usuario usuario = usuarioOpt.get();
	        boolean existe = usuario.getFavoritos().stream().anyMatch(e -> e.getProduto() == produto);
	        if(!existe) {
	        Favorito favorito = new Favorito(usuario, produto);
	        usuario.getFavoritos().add(favorito);
	        usuario = usuarioRepository.save(usuario);
	        return (usuario);
	        }
	        
	        return (usuario);
	    }
	  
	  public Usuario RemoverDaListaDeDesejos(Long id, Produto produto) {
	        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
	        if (usuarioOpt == null) {
				throw new UsuarioException("Usuario não existe");
			}
	        Usuario usuario = usuarioOpt.get();
	        Optional<Favorito> produtoOpt = usuario.getFavoritos().stream().filter(e -> e.getProduto().equals(produto)).findFirst();
	        Favorito favorito = produtoOpt.get();
	        usuario.getFavoritos().remove(favorito);
	        usuario = usuarioRepository.save(usuario);
	        favoritoRepository.delete(favorito);
	        return (usuario);
	  }
	    
	

	public Optional<Usuario> remover(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new UsuarioException("Usuario não existe");
		}
		usuarioRepository.deleteById(id);
		return usuarioRepository.findById(id);
	}

}

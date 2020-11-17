package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Usuarios;
import com.projeto.tcc.repositorios.UsuariosRepositorio;

@Service
public class UsuariosServico {

	@Autowired
	private UsuariosRepositorio usuariosRepositorio;

	public List<Usuarios> listar() {
		return usuariosRepositorio.findAll();
	}

	public Usuarios buscarPeloCodigo(Long codigo) {
		return usuariosRepositorio.findById(codigo).orElse(null);
	}

	public Usuarios salvar(Usuarios usuarios) {
		return usuariosRepositorio.save(usuarios);
	}

	public void deletar(Long id) {
		usuariosRepositorio.deleteById(id);
	}

	public Usuarios atualizar(Long id, Usuarios usuarios) {
		Usuarios entidade = usuariosRepositorio.getOne(id);
		atualizarData(entidade, usuarios);
		return usuariosRepositorio.save(entidade);
	}

	private void atualizarData(Usuarios entidade, Usuarios usuarios) {
		entidade.setNome(usuarios.getNome());
	}

	public Usuarios logar(Usuarios usuarios) {
	    Usuarios usuario = usuariosRepositorio.findByNome(usuarios.getNome());
	    
	    if(usuario == null) return null;
	    
	    if(usuario.getSenha().equals(usuarios.getSenha()) ) {
	    	return usuario;
	    }
		return null;
	}

}

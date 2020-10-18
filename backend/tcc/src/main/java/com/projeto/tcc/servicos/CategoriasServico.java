package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Categorias;
import com.projeto.tcc.repositorios.CategoriasRepositorio;

@Service
public class CategoriasServico {

	@Autowired
	private CategoriasRepositorio categoriasRepositorio;

	public List<Categorias> listar() {
		return categoriasRepositorio.findAll();
	}

	public Categorias buscarPeloCodigo(Long codigo) {
		return categoriasRepositorio.findById(codigo).orElse(null);
	}

	public Categorias salvar(Categorias categorias) {
		return categoriasRepositorio.save(categorias);
	}

	public void deletar(Long id) {
		categoriasRepositorio.deleteById(id);
	}

	public Categorias atualizar(Long id, Categorias categorias) {
		Categorias entidade = categoriasRepositorio.getOne(id);
		atualizarData(entidade, categorias);
		return categoriasRepositorio.save(entidade);
	}

	private void atualizarData(Categorias entidade, Categorias categorias) {
		entidade.setNome(categorias.getNome());
		entidade.setDescricao(categorias.getDescricao());
	}

}

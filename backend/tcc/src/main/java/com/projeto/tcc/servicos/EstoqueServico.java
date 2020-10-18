package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Estoque;
import com.projeto.tcc.repositorios.EstoqueRepositorio;

@Service
public class EstoqueServico {

	@Autowired
	private EstoqueRepositorio estoquesRepositorio;

	public List<Estoque> listar() {
		return estoquesRepositorio.findAll();
	}

	public Estoque buscarPeloCodigo(Long codigo) {
		return estoquesRepositorio.findById(codigo).orElse(null);
	}

	public Estoque salvar(Estoque estoque) {
		return estoquesRepositorio.save(estoque);
	}

	public void deletar(Long id) {
		estoquesRepositorio.deleteById(id);
	}

	public Estoque atualizar(Long id, Estoque estoque) {
		Estoque entidade = estoquesRepositorio.getOne(id);
		atualizarData(entidade, estoque);
		return estoquesRepositorio.save(entidade);
	}

	private void atualizarData(Estoque entidade, Estoque estoque) {
		entidade.setQuantidade(estoque.getQuantidade());
		entidade.setQtdMax(estoque.getQtdMax());
		entidade.setQtdMin(estoque.getQtdMin());
	}

}

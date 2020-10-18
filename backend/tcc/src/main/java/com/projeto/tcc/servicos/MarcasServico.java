package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Marcas;
import com.projeto.tcc.repositorios.MarcasRepositorio;

@Service
public class MarcasServico {

	@Autowired
	private MarcasRepositorio marcasRepositorio;

	public List<Marcas> listar() {
		return marcasRepositorio.findAll();
	}

	public Marcas buscarPeloCodigo(Long codigo) {
		return marcasRepositorio.findById(codigo).orElse(null);
	}

	public Marcas salvar(Marcas marcas) {
		return marcasRepositorio.save(marcas);
	}

	public void deletar(Long id) {
		marcasRepositorio.deleteById(id);
	}

	public Marcas atualizar(Long id, Marcas marcas) {
		Marcas entidade = marcasRepositorio.getOne(id);
		atualizarData(entidade, marcas);
		return marcasRepositorio.save(entidade);
	}

	private void atualizarData(Marcas entidade, Marcas marcas) {
		entidade.setNome(marcas.getNome());
	}

}

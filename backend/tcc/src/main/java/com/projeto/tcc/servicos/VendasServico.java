package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Vendas;
import com.projeto.tcc.repositorios.VendasRepositorio;

@Service
public class VendasServico {

	@Autowired
	private VendasRepositorio vendasRepositorio;

	public List<Vendas> listar() {
		return vendasRepositorio.findAll();
	}

	public Vendas buscarPeloCodigo(Long codigo) {
		return vendasRepositorio.findById(codigo).orElse(null);
	}

	public Vendas salvar(Vendas vendas) {
		return vendasRepositorio.save(vendas);
	}

	public void deletar(Long id) {
		vendasRepositorio.deleteById(id);
	}

	public Vendas atualizar(Long id, Vendas vendas) {
		Vendas entidade = vendasRepositorio.getOne(id);
		atualizarData(entidade, vendas);
		return vendasRepositorio.save(entidade);
	}

	private void atualizarData(Vendas entidade, Vendas vendas) {
		entidade.setQuantidade(vendas.getQuantidade());
		entidade.setValor(vendas.getValor());
		entidade.setData(vendas.getData());
		entidade.setUsuarios(vendas.getUsuarios());
		entidade.setProdutos(vendas.getProdutos());
	}

}

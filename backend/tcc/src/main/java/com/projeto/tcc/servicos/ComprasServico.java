package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Compras;
import com.projeto.tcc.repositorios.ComprasRepositorio;

@Service
public class ComprasServico {
	
	@Autowired
	private ComprasRepositorio comprasRepositorio;
	
	public List<Compras> listar() {
		return comprasRepositorio.findAll();
	}
	
	public Compras buscarPeloCodigo(Long codigo) {
		return comprasRepositorio.findById(codigo).orElse(null);
	}
	
	public Compras salvar(Compras compras) {
		return comprasRepositorio.save(compras);
	}
    
	public void deletar(Long id) {
		comprasRepositorio.deleteById(id);
	}
	
	public Compras atualizar(Long id, Compras compras) {
		Compras entidade = comprasRepositorio.getOne(id);
		atualizarData(entidade, compras);
		return comprasRepositorio.save(entidade);
	}

	private void atualizarData(Compras entidade, Compras compras) {
	entidade.setQuantidade(compras.getQuantidade());	
	entidade.setValor(compras.getValor());
	entidade.setData(compras.getData());
	}

}

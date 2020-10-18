package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Produtos;
import com.projeto.tcc.repositorios.ProdutosRepositorio;

@Service
public class ProdutosServico {

	@Autowired
	private ProdutosRepositorio produtosRepositorio;

	public List<Produtos> listar() {
		return produtosRepositorio.findAll();
	}

	public Produtos buscarPeloCodigo(Long codigo) {
		return produtosRepositorio.findById(codigo).orElse(null);
	}

	public Produtos salvar(Produtos produtos) {
		return produtosRepositorio.save(produtos);
	}

	public void deletar(Long id) {
		produtosRepositorio.deleteById(id);
	}

	public Produtos atualizar(Long id, Produtos produtos) {
		Produtos entidade = produtosRepositorio.getOne(id);
		atualizarData(entidade, produtos);
		return produtosRepositorio.save(entidade);
	}

	private void atualizarData(Produtos entidade, Produtos produtos) {
		entidade.setNome(produtos.getNome());
		entidade.setDescricao(produtos.getDescricao());
		entidade.setValorCompra(produtos.getValorCompra());
		entidade.setValorVenda(produtos.getValorVenda());
	
	}

}

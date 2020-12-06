package com.projeto.tcc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto.tcc.entidades.Categorias;

public interface CategoriasRepositorio extends JpaRepository<Categorias, Long> {

	@Query("SELECT a from Categorias a where "
			+ " (a.id = :id or :id = 0) and "
			+ " (a.nome like :nome or :nome = '') and "
			+ " (a.descricao like :descricao or :descricao = '')  "
			)
	List<Categorias> buscarFiltrada(Long id, String nome, String descricao);

}

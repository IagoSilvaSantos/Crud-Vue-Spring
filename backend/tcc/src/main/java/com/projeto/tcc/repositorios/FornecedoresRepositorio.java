package com.projeto.tcc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

 
import com.projeto.tcc.entidades.Fornecedores;

public interface FornecedoresRepositorio extends JpaRepository<Fornecedores, Long> {


	
	@Query("SELECT a from Fornecedores a where "
			+ " (a.id = :id or :id = 0) and "
			+ " (a.razaoSocial like :razaoSocial or :razaoSocial = '') and "
			+ " (a.nomeFantasia like :nomeFantasia or :nomeFantasia = '') and "
			+ " (a.representante like :representante or :representante = '') and "
			+ " (a.telefone like :telefone or :telefone = '') and "
			+ " (a.cnpj like :cnpj or :cnpj = '')  "
			)
	List<Fornecedores> buscarFiltrada(Long id, String razaoSocial, String nomeFantasia, String representante, String telefone, String cnpj);

}

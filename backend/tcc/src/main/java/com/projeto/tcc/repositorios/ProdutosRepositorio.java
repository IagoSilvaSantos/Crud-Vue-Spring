package com.projeto.tcc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tcc.entidades.Produtos;

public interface ProdutosRepositorio extends JpaRepository<Produtos, Long> {

}

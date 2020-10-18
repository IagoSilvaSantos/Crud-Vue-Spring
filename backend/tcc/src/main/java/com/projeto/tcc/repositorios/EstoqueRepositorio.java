package com.projeto.tcc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tcc.entidades.Estoque;

public interface EstoqueRepositorio extends JpaRepository<Estoque, Long> {

}

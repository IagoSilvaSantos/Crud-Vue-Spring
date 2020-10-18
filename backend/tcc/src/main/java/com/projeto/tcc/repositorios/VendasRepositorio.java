package com.projeto.tcc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tcc.entidades.Vendas;

public interface VendasRepositorio extends JpaRepository<Vendas, Long> {

}

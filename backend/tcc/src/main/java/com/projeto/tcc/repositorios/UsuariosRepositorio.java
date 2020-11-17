package com.projeto.tcc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tcc.entidades.Usuarios;

public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long> {

	Usuarios findByNome(String nome);

}

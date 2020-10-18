package com.projeto.tcc.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tcc.entidades.Usuarios;
import com.projeto.tcc.servicos.UsuariosServico;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuariosControlador {

	@Autowired
	UsuariosServico usuariosServico;

	@GetMapping
	public List<Usuarios> listar() {
		return usuariosServico.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> buscarPeloCodigo(@PathVariable Long id) {
		Usuarios usuarios = usuariosServico.buscarPeloCodigo(id);
		return usuarios != null ? ResponseEntity.ok(usuarios) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Usuarios> salvar(@RequestBody Usuarios usuarios) {
		usuarios = usuariosServico.salvar(usuarios);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarios);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuariosServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuarios> atualizar(@PathVariable Long id, @RequestBody Usuarios usuarios) {
		usuarios = usuariosServico.atualizar(id, usuarios);
		return ResponseEntity.ok().body(usuarios);
	}

}

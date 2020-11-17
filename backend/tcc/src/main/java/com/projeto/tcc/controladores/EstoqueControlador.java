package com.projeto.tcc.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tcc.entidades.Estoque;
import com.projeto.tcc.servicos.EstoqueServico;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/estoque")
public class EstoqueControlador {

	@Autowired
	EstoqueServico estoqueServico;

	@GetMapping
	public List<Estoque> listar() {
		return estoqueServico.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estoque> buscarPeloCodigo(@PathVariable Long id) {
		Estoque estoque = estoqueServico.buscarPeloCodigo(id);
		return estoque != null ? ResponseEntity.ok(estoque) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Estoque> salvar(@RequestBody Estoque estoque) {
		estoque = estoqueServico.salvar(estoque);
		return ResponseEntity.status(HttpStatus.CREATED).body(estoque);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		estoqueServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Estoque> atualizar(@PathVariable Long id, @RequestBody Estoque estoque) {
		estoque = estoqueServico.atualizar(id, estoque);
		return ResponseEntity.ok().body(estoque);
	}

}

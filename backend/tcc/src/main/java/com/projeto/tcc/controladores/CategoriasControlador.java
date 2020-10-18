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

import com.projeto.tcc.entidades.Categorias;
import com.projeto.tcc.servicos.CategoriasServico;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasControlador {

	@Autowired
	CategoriasServico categoriasServico;

	@GetMapping
	public List<Categorias> listar() {
		return categoriasServico.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categorias> buscarPeloCodigo(@PathVariable Long id) {
		Categorias categorias = categoriasServico.buscarPeloCodigo(id);
		return categorias != null ? ResponseEntity.ok(categorias) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Categorias> salvar(@RequestBody Categorias categorias) {
		categorias = categoriasServico.salvar(categorias);
		return ResponseEntity.status(HttpStatus.CREATED).body(categorias);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		categoriasServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Categorias> atualizar(@PathVariable Long id, @RequestBody Categorias categorias) {
		categorias = categoriasServico.atualizar(id, categorias);
		return ResponseEntity.ok().body(categorias);
	}

}

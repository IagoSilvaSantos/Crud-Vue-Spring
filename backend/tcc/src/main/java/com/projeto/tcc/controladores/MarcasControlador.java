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

import com.projeto.tcc.entidades.Marcas;
import com.projeto.tcc.servicos.MarcasServico;

@RestController
@RequestMapping(value = "/marcas")
public class MarcasControlador {

	@Autowired
	MarcasServico marcasServico;

	@GetMapping
	public List<Marcas> listar() {
		return marcasServico.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Marcas> buscarPeloCodigo(@PathVariable Long id) {
		Marcas marcas = marcasServico.buscarPeloCodigo(id);
		return marcas != null ? ResponseEntity.ok(marcas) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Marcas> salvar(@RequestBody Marcas marcas) {
		marcas = marcasServico.salvar(marcas);
		return ResponseEntity.status(HttpStatus.CREATED).body(marcas);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		marcasServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Marcas> atualizar(@PathVariable Long id, @RequestBody Marcas marcas) {
		marcas = marcasServico.atualizar(id, marcas);
		return ResponseEntity.ok().body(marcas);
	}

}

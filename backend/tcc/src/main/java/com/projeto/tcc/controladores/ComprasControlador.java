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

import com.projeto.tcc.entidades.Compras;
import com.projeto.tcc.servicos.ComprasServico;

@RestController
@RequestMapping(value = "/compras")
public class ComprasControlador {

	@Autowired
	ComprasServico comprasServico;

	@GetMapping
	public List<Compras> listar() {
		return comprasServico.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Compras> buscarPeloCodigo(@PathVariable Long id) {
		Compras compras = comprasServico.buscarPeloCodigo(id);
		return compras != null ? ResponseEntity.ok(compras) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Compras> salvar(@RequestBody Compras compras) {
		compras = comprasServico.salvar(compras);
		return ResponseEntity.status(HttpStatus.CREATED).body(compras);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		comprasServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Compras> atualizar(@PathVariable Long id, @RequestBody Compras compras) {
		compras = comprasServico.atualizar(id, compras);
		return ResponseEntity.ok().body(compras);
	}

}

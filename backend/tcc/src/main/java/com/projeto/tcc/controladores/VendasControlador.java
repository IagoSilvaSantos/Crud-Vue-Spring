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

import com.projeto.tcc.entidades.Vendas;
import com.projeto.tcc.servicos.VendasServico;

@RestController
@RequestMapping(value = "/vendas")
public class VendasControlador {

	@Autowired
	VendasServico vendasServico;

	@GetMapping
	public List<Vendas> listar() {
		return vendasServico.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vendas> buscarPeloCodigo(@PathVariable Long id) {
		Vendas vendas = vendasServico.buscarPeloCodigo(id);
		return vendas != null ? ResponseEntity.ok(vendas) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Vendas> salvar(@RequestBody Vendas vendas) {
		vendas = vendasServico.salvar(vendas);
		return ResponseEntity.status(HttpStatus.CREATED).body(vendas);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		vendasServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Vendas> atualizar(@PathVariable Long id, @RequestBody Vendas vendas) {
		vendas = vendasServico.atualizar(id, vendas);
		return ResponseEntity.ok().body(vendas);
	}

}

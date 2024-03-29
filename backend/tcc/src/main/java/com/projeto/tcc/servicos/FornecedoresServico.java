package com.projeto.tcc.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tcc.entidades.Fornecedores;
import com.projeto.tcc.repositorios.FornecedoresRepositorio;

@Service
public class FornecedoresServico {

	@Autowired
	private FornecedoresRepositorio fornecedoresRepositorio;

	public List<Fornecedores> listar() {
		return fornecedoresRepositorio.findAll();
	}

	public Fornecedores buscarPeloCodigo(Long codigo) {
		return fornecedoresRepositorio.findById(codigo).orElse(null);
	}

	public Fornecedores salvar(Fornecedores fornecedores) {
		return fornecedoresRepositorio.save(fornecedores);
	}

	public void deletar(Long id) {
		fornecedoresRepositorio.deleteById(id);
	}

	public Fornecedores atualizar(Long id, Fornecedores fornecedores) {
		Fornecedores entidade = fornecedoresRepositorio.getOne(id);
		atualizarData(entidade, fornecedores);
		return fornecedoresRepositorio.save(entidade);
	}

	private void atualizarData(Fornecedores entidade, Fornecedores fornecedores) {
		entidade.setRazaoSocial(fornecedores.getRazaoSocial());
		entidade.setNomeFantasia(fornecedores.getNomeFantasia());
		entidade.setRepresentante(fornecedores.getRepresentante());
		entidade.setCnpj(fornecedores.getCnpj());
		entidade.setTelefone(fornecedores.getTelefone());
	}

	public List<Fornecedores> buscarFiltrada(Long id, String razaoSocial, String nomeFantasia, String representante, String telefone,
			String cnpj) {
 
		id = id == null ? 0 : id;
		razaoSocial = razaoSocial == null ? "" : "%"+razaoSocial+"%";
		nomeFantasia = nomeFantasia == null ? "" : "%"+nomeFantasia+"%";
		representante = representante == null ? "" : "%"+representante+"%";
		telefone = telefone == null ? "" : "%"+telefone+"%";
		cnpj = cnpj == null ? "" : "%"+cnpj+"%";
		return fornecedoresRepositorio.buscarFiltrada(id, razaoSocial,nomeFantasia, representante, telefone, cnpj);

	}

}

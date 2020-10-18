package com.projeto.tcc.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produtos")
public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String nome;
	private String descricao;

	@NotNull	
	@Column(name = "valor_compra")
	private float valorCompra;

	@NotNull
	@Column(name = "valor_venda")
	private float valorVenda;

	@ManyToOne
	@JoinColumn(name = "estoque_id")
	private Estoque estoque;

	@ManyToOne
	@JoinColumn(name = "marcas_id")
	private Marcas marcas;

	@ManyToOne
	@JoinColumn(name = "categorias_id")
	private Categorias categorias;

	public Produtos() {

	}

	public Produtos(Long id, String nome, String descricao, float valorCompra, float valorVenda, String imagem,
			Estoque estoque, Marcas marcas, Categorias categorias) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valorCompra = valorCompra;
		this.valorVenda = valorVenda;		
		this.estoque = estoque;
		this.marcas = marcas;
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(float valorCompra) {
		this.valorCompra = valorCompra;
	}

	public float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Marcas getMarcas() {
		return marcas;
	}

	public void setMarcas(Marcas marcas) {
		this.marcas = marcas;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorias == null) ? 0 : categorias.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marcas == null) ? 0 : marcas.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + Float.floatToIntBits(valorCompra);
		result = prime * result + Float.floatToIntBits(valorVenda);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		if (categorias == null) {
			if (other.categorias != null)
				return false;
		} else if (!categorias.equals(other.categorias))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (estoque == null) {
			if (other.estoque != null)
				return false;
		} else if (!estoque.equals(other.estoque))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marcas == null) {
			if (other.marcas != null)
				return false;
		} else if (!marcas.equals(other.marcas))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Float.floatToIntBits(valorCompra) != Float.floatToIntBits(other.valorCompra))
			return false;
		if (Float.floatToIntBits(valorVenda) != Float.floatToIntBits(other.valorVenda))
			return false;
		return true;
	}

}

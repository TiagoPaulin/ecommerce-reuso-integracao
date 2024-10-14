package com.reuso.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reuso.entities.abstracts.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pessoaJuridica")
public class PessoaJuridica extends Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fantasia;
    private String cnpj;

	@OneToMany(mappedBy = "pessoaJuridicaTelefone")
	private List<Telefone> telefones = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pjVendedor")
	private List<Ingresso> ingressos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pessoaJuridicaAnuncio")
	private List<Anuncio> anuncios = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pjComprador")
	private List<Venda> compras = new ArrayList<>();
	
	public PessoaJuridica() {
	}

	public PessoaJuridica(Long id, String fantasia, String email, String senha, String cnpj) {
		super.setId(id);
		super.setEmail(email);
		super.setSenha(senha);
		this.fantasia = fantasia;
		this.cnpj = cnpj;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public List<Venda> getCompras() {
		return compras;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaJuridica other = (PessoaJuridica) obj;
		return Objects.equals(super.getId(), other.getId());
	}
}

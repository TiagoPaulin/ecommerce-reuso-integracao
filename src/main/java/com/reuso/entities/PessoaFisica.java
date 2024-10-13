package com.reuso.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reuso.entities.abstracts.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pessoaFisica")
public class PessoaFisica extends Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	private String nomeCompleto;
	private Date dataNascimento;
    private String cpf;
    @JsonIgnore
	@OneToMany(mappedBy = "pessoaFisicaTelefone")
	private List<Telefone> telefones = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pfVendedor")
	private List<Ingresso> ingressos = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "pessoaFisicaAnuncio")
	private List<Anuncio> anuncios = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pfComprador")
	private List<Venda> compras = new ArrayList<>();
	
	public PessoaFisica() {
	}

	public PessoaFisica(Long id, String nomeCompleto, String email, String senha, Date dataNascimento, String cpf) {
		super.setId(id);
		super.setEmail(email);
		super.setSenha(senha);
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
		PessoaFisica other = (PessoaFisica) obj;
		return Objects.equals(super.getId(), other.getId());
	}	
}

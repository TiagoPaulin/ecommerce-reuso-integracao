package com.reuso.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reuso.entities.state.venda.EmProcessamento;
import com.reuso.entities.state.venda.EstadoVendaBase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "anuncio_id")
	private Anuncio anuncioVenda;

	@ManyToOne
	@JoinColumn(name="pf_v_id")
	private PessoaFisica pfComprador;

	@ManyToOne
	@JoinColumn(name="pj_v_id")
	private PessoaJuridica pjComprador;
	
	@ManyToOne
    @JoinColumn(name = "estadoVenda_id")
    private EstadoVendaBase estadoVenda;
	
	public Venda() {
	}
	
	public Venda(Long id, Anuncio anuncio, PessoaFisica pessoaFisica) {
		super();
		this.id = id;
		this.anuncioVenda = anuncio;
		this.pfComprador = pessoaFisica;
		this.pjComprador = null;
		this.estadoVenda = new EmProcessamento();
	}
	
	public Venda(Long id, Anuncio anuncio, PessoaJuridica pessoaJuridica) {
		super();
		this.id = id;
		this.anuncioVenda = anuncio;
		this.pfComprador = null;
		this.pjComprador = pessoaJuridica;
		this.estadoVenda = new EmProcessamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Anuncio getAnuncioVenda() {
		return anuncioVenda;
	}

	public void setAnuncioVenda(Anuncio anuncioVenda) {
		this.anuncioVenda = anuncioVenda;
	}
	
	public PessoaFisica getPfComprador() {
		return pfComprador;
	}

	public void setPfComprador(PessoaFisica pfComprador) {
		this.pfComprador = pfComprador;
	}

	public PessoaJuridica getPjComprador() {
		return pjComprador;
	}

	public void setPjComprador(PessoaJuridica pjComprador) {
		this.pjComprador = pjComprador;
	}
	
	public EstadoVendaBase getEstadoVenda() {
		return estadoVenda;
	}

	public void setEstadoVenda(EstadoVendaBase estadoVenda) {
		this.estadoVenda = estadoVenda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}

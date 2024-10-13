package com.reuso.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reuso.entities.state.anuncio.EstadoAnuncioBase;
import com.reuso.entities.state.anuncio.Liberado;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_anuncio")
public class Anuncio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy="anuncio")
    private List<Ingresso> ingressos = new ArrayList<>();
    

    @ManyToOne
	@JoinColumn(name = "pj_a_id")
	private PessoaJuridica pessoaJuridicaAnuncio;

    @ManyToOne
	@JoinColumn(name = "pf_a_id")
	private PessoaFisica pessoaFisicaAnuncio;
    
    @ManyToOne
    @JoinColumn(name = "estadoAnuncio_id")
    private EstadoAnuncioBase estadoAnuncio;
    
    @OneToMany(mappedBy = "anuncioVenda")
    private List<Venda> vendas = new ArrayList<>();
   
    public Anuncio() {
    }
    
	public Anuncio(Long id, String titulo, String descricao, PessoaFisica pessoaFisica, EstadoAnuncioBase estadoAnuncio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.pessoaFisicaAnuncio = pessoaFisica;
		this.pessoaJuridicaAnuncio = null;
		this.estadoAnuncio = estadoAnuncio;
	}
	
	public Anuncio(Long id, String titulo, String descricao, PessoaJuridica pessoaJuridica, EstadoAnuncioBase estadoAnuncio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.pessoaFisicaAnuncio = null;
		this.pessoaJuridicaAnuncio = pessoaJuridica;
		this.estadoAnuncio = estadoAnuncio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public PessoaJuridica getPessoaJuridicaAnuncio() {
		return pessoaJuridicaAnuncio;
	}

	public void setPessoaJuridicaAnuncio(PessoaJuridica pessoaJuridicaAnuncio) {
		this.pessoaJuridicaAnuncio = pessoaJuridicaAnuncio;
	}

	public PessoaFisica getPessoaFisicaAnuncio() {
		return pessoaFisicaAnuncio;
	}

	public void setPessoaFisicaAnuncio(PessoaFisica pessoaFisicaAnuncio) {
		this.pessoaFisicaAnuncio = pessoaFisicaAnuncio;
	}

	public EstadoAnuncioBase getEstadoAnuncio() {
		return estadoAnuncio;
	}

	public void setEstadoAnuncio(EstadoAnuncioBase estado) {
		this.estadoAnuncio = estado;
	}
	
	public List<Venda> getVendas() {
		return vendas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anuncio other = (Anuncio) obj;
		return Objects.equals(id, other.id);
	}
}

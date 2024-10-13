package com.reuso.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ingresso")
public class Ingresso implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
    private String descricao;
    private int quantidade;
    private float valor;
    private boolean inteiro;

    @ManyToOne
	@JoinColumn(name = "pj_i_id")
    private PessoaJuridica pjVendedor;

    @ManyToOne
	@JoinColumn(name = "pf_i_id")
    private PessoaFisica pfVendedor;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "anuncio_id")
    private Anuncio anuncio;
    
    public Ingresso() {
    }

	public Ingresso(Long id, String titulo, String descricao, int quantidade, float valor, boolean inteiro, PessoaFisica pessoaFisica, Evento evento, Anuncio anuncio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.inteiro = inteiro;
		this.pfVendedor = pessoaFisica;
		this.evento = evento;
		this.anuncio = anuncio;
	}
	
	public Ingresso(Long id, String titulo, String descricao, int quantidade, float valor, boolean inteiro, PessoaJuridica pessoaJuridica, Evento evento, Anuncio anuncio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.inteiro = inteiro;
		this.pjVendedor = pessoaJuridica;
		this.evento = evento;
		this.anuncio = anuncio;
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

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public boolean isInteiro() {
		return inteiro;
	}

	public void setInteiro(boolean inteiro) {
		this.inteiro = inteiro;
	}

	public PessoaJuridica getPjVendedor() {
		return pjVendedor;
	}

	public void setPjVendedor(PessoaJuridica pjVendedor) {
		this.pjVendedor = pjVendedor;
	}

	public PessoaFisica getPfVendedor() {
		return pfVendedor;
	}

	public void setPfVendedor(PessoaFisica pfVendedor) {
		this.pfVendedor = pfVendedor;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
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
		Ingresso other = (Ingresso) obj;
		return Objects.equals(id, other.id);
	}
}

package com.reuso.entities.state.anuncio;

import com.reuso.entities.Anuncio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LIBERADO")
public class Liberado extends EstadoAnuncioBase{


	public Liberado() {}
	public Liberado(Long id) {
		super(id);
	}

	@Override
	public void publicar(Anuncio anuncio) {
	}

	@Override
	public void bloquear(Anuncio anuncio) {
		anuncio.setEstadoAnuncio(new Bloqueado(this.getId()));
	}

	@Override
	public void editar(Anuncio anuncio, String titulo, String descricao) {
	}

	@Override
	public void vendido(Anuncio anuncio) {
		anuncio.setEstadoAnuncio(new Vendido(this.getId()));
	}

	@Override
	public String estado() {
		return "Liberado";
	}
}

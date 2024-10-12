package com.reuso.entities.state.anuncio;

import com.reuso.entities.Anuncio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VENDIDO")
public class Vendido extends EstadoAnuncioBase {

	public Vendido() {}

	public Vendido(Long id) {
		super(id);
	}

	@Override
	public void publicar(Anuncio anuncio) {
	}

	@Override
	public void bloquear(Anuncio anuncio) {
	}

	@Override
	public void vendido(Anuncio anuncio) {
	}

	@Override
	public void editar(Anuncio anuncio, String titulo, String descricao) {
	}

	@Override
	public String estado() {
		return "Vendido";
	}

}

package com.reuso.entities.state.anuncio;

import com.reuso.entities.Anuncio;

public interface EstadoAnuncio {
	void publicar(Anuncio anuncio);
    void bloquear(Anuncio anuncio);
    void vendido(Anuncio anuncio);
    void editar(Anuncio anuncio, String titulo, String descricao);
    String estado();
}

package com.reuso.factory;

import com.reuso.entities.*;

public class IngressoFactory {
	public static Ingresso createIngresso(String tipo, Long id, String titulo, String descricao, int quantidade, float valor, boolean inteiro,
										  PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica, Evento evento, Anuncio anuncio) {

        if (tipo.equalsIgnoreCase("i")) {
        	if(pessoaFisica != null) {
        		return new Ingresso(null, titulo, descricao, quantidade, valor, inteiro, pessoaFisica, evento, anuncio);
        	}else {
        		return new Ingresso(null, titulo, descricao, quantidade, valor, inteiro, pessoaJuridica, evento, anuncio);
        	}
        } else if (tipo.equalsIgnoreCase("m")) {
        	if(pessoaFisica != null) {
        		return new Ingresso(null, titulo, descricao, quantidade, valor, inteiro, pessoaFisica, evento, anuncio);
        	}else {
        		return new Ingresso(null, titulo, descricao, quantidade, valor, inteiro, pessoaJuridica, evento, anuncio);
        	}
        } else {
            throw new IllegalArgumentException("Tipo de ingresso desconhecido.");
        }
    }
}

package com.reuso.factory;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.reuso.entities.PessoaFisica;
import com.reuso.entities.PessoaJuridica;
import com.reuso.entities.abstracts.Usuario;
import com.reuso.repositories.PessoaFisicaRepository;
import com.reuso.repositories.PessoaJuridicaRepository;
import com.reuso.repositories.TelefoneRepository;

public class UsuarioFactory {
	
	public static Usuario createUser(String tipo, String nomeCompleto, String email, String senha, String dataNascimento, String identificador) {
        if (tipo.equalsIgnoreCase("f")) {
            return new PessoaFisica(null, nomeCompleto, email, senha, Date.valueOf(dataNascimento), identificador);
        } else if (tipo.equalsIgnoreCase("j")) {
            return new PessoaJuridica(null, nomeCompleto, email, senha, identificador);
        } else {
            throw new IllegalArgumentException("Tipo de usuário desconhecido.");
        }
    }
}

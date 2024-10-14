package com.reuso.services;

import com.reuso.entities.PessoaFisica;
import com.reuso.entities.PessoaJuridica;
import com.reuso.entities.abstracts.Usuario;
import com.reuso.repositories.PessoaFisicaRepository;
import com.reuso.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    public Map<String, Object> verificarLogin(String email, String senha) {

        Map<String, Object> response = new HashMap<>();

        Optional<PessoaJuridica> pessoaJuridicaOpt = pessoaJuridicaRepository.findByEmailAndSenha(email, senha);

        if (pessoaJuridicaOpt.isPresent()) {
            PessoaJuridica pessoaJuridica = pessoaJuridicaOpt.get();

            if (pessoaJuridica.getEmail().equals(email) &&
                    pessoaJuridica.getSenha().equals(senha)) {
                response.put("id", pessoaJuridica.getId());
                response.put("tipo", "juridica");
                return response;
            }
        }

        Optional<PessoaFisica> pessoaFisicaOpt = pessoaFisicaRepository.findByEmailAndSenha(email, senha);

        if (pessoaFisicaOpt.isPresent()) {
            PessoaFisica pessoaFisica = pessoaFisicaOpt.get();

            if (pessoaFisica.getEmail().equals(email) &&
                    pessoaFisica.getSenha().equals(senha)) {
                response.put("id", pessoaFisica.getId());
                response.put("tipo", "fisica");
                return response;
            }
        }

        return null;

    }

}

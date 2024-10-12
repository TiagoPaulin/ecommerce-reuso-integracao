package com.reuso.services;

import com.reuso.entities.PessoaFisica;
import com.reuso.entities.PessoaJuridica;
import com.reuso.entities.abstracts.Usuario;
import com.reuso.repositories.PessoaFisicaRepository;
import com.reuso.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    public boolean verificarLogin(String email, String senha) {

        Optional<PessoaJuridica> pessoaJuridicaOpt = pessoaJuridicaRepository.findByEmailAndSenha(email, senha);

        if (pessoaJuridicaOpt.isPresent()) {
            PessoaJuridica pessoaJuridica = pessoaJuridicaOpt.get();

            if (pessoaJuridica.getEmail().equals(email) &&
                    pessoaJuridica.getSenha().equals(senha)) {
                return true;
            }
        }

        Optional<PessoaFisica> pessoaFisicaOpt = pessoaFisicaRepository.findByEmailAndSenha(email, senha);

        if (pessoaFisicaOpt.isPresent()) {
            PessoaFisica pessoaFisica = pessoaFisicaOpt.get();

            if (pessoaFisica.getEmail().equals(email) &&
                    pessoaFisica.getSenha().equals(senha)) {
                return true;
            }
        }

        return false;

    }

}

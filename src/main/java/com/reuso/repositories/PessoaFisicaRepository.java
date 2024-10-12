package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.PessoaFisica;

import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{

    Optional<PessoaFisica> findByEmailAndSenha(String email, String senha);


}

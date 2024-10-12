package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.PessoaJuridica;

import java.util.Optional;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>{

    Optional<PessoaJuridica> findByEmailAndSenha(String email, String senha);

}

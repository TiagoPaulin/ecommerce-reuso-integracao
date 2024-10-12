package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
	
}

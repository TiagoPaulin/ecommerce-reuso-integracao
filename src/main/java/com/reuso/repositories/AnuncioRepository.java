package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
	
}

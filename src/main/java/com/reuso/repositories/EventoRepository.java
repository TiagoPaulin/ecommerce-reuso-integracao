package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{
	
}

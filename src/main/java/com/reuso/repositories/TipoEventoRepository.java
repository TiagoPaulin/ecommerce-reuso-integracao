package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.TipoEvento;

public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long>{
	
}

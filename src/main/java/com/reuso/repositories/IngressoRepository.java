package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long>{
	
}

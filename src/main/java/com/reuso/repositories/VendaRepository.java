package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
	
}

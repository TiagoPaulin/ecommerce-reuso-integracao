package com.reuso.repositories;

import com.reuso.entities.state.anuncio.EstadoAnuncioBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoAnuncioRepository extends JpaRepository<EstadoAnuncioBase, Long>{
}

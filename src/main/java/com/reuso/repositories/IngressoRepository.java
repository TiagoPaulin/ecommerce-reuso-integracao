package com.reuso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuso.entities.Ingresso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Long>{

    @Query("SELECT i FROM Ingresso i WHERE " +
            "LOWER(i.titulo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(i.descricao) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Ingresso> searchByTitleOrDescription(@Param("search") String search);

}

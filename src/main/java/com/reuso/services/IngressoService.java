package com.reuso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.Ingresso;
import com.reuso.entities.Telefone;
import com.reuso.repositories.IngressoRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IngressoService {

	@Autowired
	private IngressoRepository repository;
	
	public List<Ingresso> findAll(){
		return repository.findAll();
	}
	
	public Ingresso findById(Long id) {
		Optional<Ingresso> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Ingresso insert(Ingresso obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}	
	}
	
	public Ingresso update(Long id, Ingresso obj) {
		try {
			Ingresso entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(Ingresso entity, Ingresso obj) {
		entity.setTitulo(obj.getTitulo());
		entity.setDescricao(obj.getDescricao());
		entity.setQuantidade(obj.getQuantidade());
	}
}

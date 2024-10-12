package com.reuso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.Telefone;
import com.reuso.entities.TipoEvento;
import com.reuso.repositories.TipoEventoRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoEventoService {

	@Autowired
	private TipoEventoRepository repository;
	
	public List<TipoEvento> findAll(){
		return repository.findAll();
	}
	
	public TipoEvento findById(Long id) {
		Optional<TipoEvento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public TipoEvento insert(TipoEvento obj) {
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
	
	public TipoEvento update(Long id, TipoEvento obj) {
		try {
			TipoEvento entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(TipoEvento entity, TipoEvento obj) {
		entity.setDescricao(obj.getDescricao());
	}
}

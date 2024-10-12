package com.reuso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.Telefone;
import com.reuso.repositories.TelefoneRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository repository;
	
	public List<Telefone> findAll(){
		return repository.findAll();
	}
	
	public Telefone findById(Long id) {
		Optional<Telefone> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Telefone insert(Telefone obj) {
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
	
	public Telefone update(Long id, Telefone obj) {
		try {
			Telefone entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(Telefone entity, Telefone obj) {
		entity.setDdd(obj.getDdd());
		entity.setDdi(obj.getDdi());
		entity.setNumero(obj.getNumero());
	}
}

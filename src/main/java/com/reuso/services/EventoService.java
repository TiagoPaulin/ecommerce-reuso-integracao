package com.reuso.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.Evento;
import com.reuso.repositories.EventoRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;
	
	public List<Evento> findAll(){
		return repository.findAll();
	}
	
	public Evento findById(Long id) {
		Optional<Evento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Evento insert(Evento obj) {
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
	
	public Evento update(Long id, Evento obj) {
		try {
			Evento entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(Evento entity, Evento obj) {
		entity.setDescricao(obj.getDescricao());
		entity.setData(obj.getData());
		entity.setTipoEvento(obj.getTipoEvento());		
	}
}

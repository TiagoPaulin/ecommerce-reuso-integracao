package com.reuso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.PessoaJuridica;
import com.reuso.repositories.PessoaJuridicaRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository repository;
	
	public List<PessoaJuridica> findAll(){
		return repository.findAll();
	}
	
	public PessoaJuridica findById(Long id) {
		Optional<PessoaJuridica> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public PessoaJuridica insert(PessoaJuridica obj) {
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
	
	public PessoaJuridica update(Long id, PessoaJuridica obj) {
		try {
			PessoaJuridica entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(PessoaJuridica entity, PessoaJuridica obj) {
		entity.setFantasia(obj.getFantasia());
		entity.setEmail(obj.getEmail());
		entity.setCnpj(obj.getCnpj());
	}
}

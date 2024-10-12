package com.reuso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.PessoaFisica;
import com.reuso.repositories.PessoaFisicaRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository repository;
	
	public List<PessoaFisica> findAll(){
		return repository.findAll();
	}
	
	public PessoaFisica findById(Long id) {
		Optional<PessoaFisica> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public PessoaFisica insert(PessoaFisica obj) {
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
	
	public PessoaFisica update(Long id, PessoaFisica obj) {
		try {
			PessoaFisica entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(PessoaFisica entity, PessoaFisica obj) {
		entity.setNomeCompleto(obj.getNomeCompleto());
		entity.setEmail(obj.getEmail());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setCpf(obj.getCpf());
	}
}

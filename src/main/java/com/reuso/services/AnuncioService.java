package com.reuso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.Anuncio;
import com.reuso.entities.observer.anuncio.AnuncioObserver;
import com.reuso.entities.state.anuncio.Bloqueado;
import com.reuso.entities.state.anuncio.Vendido;
import com.reuso.repositories.AnuncioRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnuncioService implements AnuncioObserver{

	@Autowired
	private AnuncioRepository repository;
	
	public List<Anuncio> findAll(){
		return repository.findAll();
	}
	
	public Anuncio findById(Long id) {
		Optional<Anuncio> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Anuncio insert(Anuncio obj) {
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
	
	public Anuncio update(Long id, Anuncio obj) {
		try {
			Anuncio entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(Anuncio entity, Anuncio obj) {
		entity.setTitulo(obj.getTitulo());
		entity.setDescricao(obj.getDescricao());	
	}
	
	public void bloquearAnuncio(Anuncio anuncio) {
        anuncio.setEstadoAnuncio(new Bloqueado(anuncio.getEstadoAnuncio().getId()));
        notificarUsuario(anuncio, "Seu anúncio: " + anuncio.getTitulo() + " foi bloqueado!");
    }

    public void venderAnuncio(Anuncio anuncio) {
        anuncio.setEstadoAnuncio(new Vendido(anuncio.getEstadoAnuncio().getId()));
        notificarUsuario(anuncio, "Seu anúncio: " + anuncio.getTitulo() + " foi vendido!");
    }
	
	private void notificarUsuario(Anuncio anuncio, String mensagem) {
        if (anuncio.getPessoaFisicaAnuncio() != null) {
            notificarAnunciante(mensagem, anuncio.getPessoaFisicaAnuncio().getEmail());
        } else if (anuncio.getPessoaJuridicaAnuncio() != null){
        	notificarAnunciante(mensagem, anuncio.getPessoaJuridicaAnuncio().getEmail());
        }	
        else{
            System.out.println("Erro: Não foi possível notificar o usuário anunciante, e-mail não informado.");
        }
    }
	
	public void notificarAnunciante(String mensagem, String emailUsuario) {
		System.out.println("Enviando e-mail para " + emailUsuario);
        System.out.println("Mensagem: " + mensagem);
	}
	
}

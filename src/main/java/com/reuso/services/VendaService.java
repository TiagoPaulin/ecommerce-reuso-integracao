package com.reuso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.reuso.entities.Venda;
import com.reuso.entities.observer.venda.VendaObserver;
import com.reuso.entities.state.venda.Cancelada;
import com.reuso.entities.state.venda.Extornada;
import com.reuso.entities.state.venda.Realizada;
import com.reuso.repositories.VendaRepository;
import com.reuso.services.exceptions.DatabaseException;
import com.reuso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VendaService implements VendaObserver{

	@Autowired
	private VendaRepository repository;
	
	public List<Venda> findAll(){
		return repository.findAll();
	}
	
	public Venda findById(Long id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Venda insert(Venda obj) {
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
	
	public Venda update(Long id, Venda obj) {
		try {
			Venda entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(Venda entity, Venda obj) {
	}

	public void realizarVenda(Venda venda) {
        venda.setEstadoVenda(new Realizada());
        notificarUsuarios(
                venda,
                "Compra do anúncio: " + venda.getAnuncioVenda().getTitulo() + " realizada!",
                "Venda do anúncio: " + venda.getAnuncioVenda().getTitulo() + " realizada!"
        );
    }

    public void cancelarVenda(Venda venda) {
        venda.setEstadoVenda(new Cancelada());
        notificarUsuarios(
                venda,
                "Compra do anúncio: " + venda.getAnuncioVenda().getTitulo() + " cancelada!",
                "Venda do anúncio: " + venda.getAnuncioVenda().getTitulo() + " cancelada!"
        );
    }
    
    public void extornarVenda(Venda venda) {
        venda.setEstadoVenda(new Extornada());
        notificarUsuarios(
                venda,
                "Compra do anúncio: " + venda.getAnuncioVenda().getTitulo() + " extornada!",
                "Venda do anúncio: " + venda.getAnuncioVenda().getTitulo() + " extornada!"
        );
    }

    private void notificarUsuarios(Venda venda, String mensagemComprador, String mensagemVendedor) {
        notificarComprador(venda, mensagemComprador);
        notificarVendedor(venda, mensagemVendedor);
    }

    private void notificarComprador(Venda venda, String mensagem) {
        if (venda.getPfComprador() != null) {
        	notificarUsuario(venda.getPfComprador().getEmail(), mensagem);
        } else if (venda.getPjComprador() != null) {
        	notificarUsuario(venda.getPjComprador().getEmail(), mensagem);
        } else {
            System.out.println("Erro: Não foi possível notificar o usuário comprador, e-mail não informado.");
        }
    }

    public void notificarVendedor(Venda venda, String mensagem) {
        if (venda.getAnuncioVenda().getPessoaFisicaAnuncio() != null) {
        	notificarUsuario(venda.getAnuncioVenda().getPessoaFisicaAnuncio().getEmail(), mensagem);
        } else if (venda.getAnuncioVenda().getPessoaJuridicaAnuncio() != null) {
        	notificarUsuario(venda.getAnuncioVenda().getPessoaJuridicaAnuncio().getEmail(), mensagem);
        } else {
            System.out.println("Erro: Não foi possível notificar o usuário vendedor, e-mail não informado.");
        }
    }
    
    public void notificarUsuario(String mensagem, String emailUsuario) {
        System.out.println("Enviando e-mail para " + emailUsuario);
        System.out.println("Mensagem: " + mensagem);
    }
}

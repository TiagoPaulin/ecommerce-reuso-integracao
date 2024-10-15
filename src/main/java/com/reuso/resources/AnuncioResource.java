package com.reuso.resources;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.reuso.entities.Ingresso;
import com.reuso.entities.PessoaFisica;
import com.reuso.entities.PessoaJuridica;
import com.reuso.entities.state.anuncio.EstadoAnuncioBase;
import com.reuso.repositories.EstadoAnuncioRepository;
import com.reuso.services.PessoaFisicaService;
import com.reuso.services.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reuso.entities.Anuncio;
import com.reuso.services.AnuncioService;


@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioResource {
	
	@Autowired
	private AnuncioService service;

	@Autowired
	private PessoaFisicaService pessoaFisicaService;
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	@Autowired
	private EstadoAnuncioRepository estadoAnuncioRepository;
	
	@GetMapping
	public ResponseEntity<List<Anuncio>> findAll(){
		List<Anuncio> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Anuncio> finById(@PathVariable Long id){
		Anuncio obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Anuncio> insert(@RequestBody Map<String, Object> dadosAnuncio){

		Anuncio obj = new Anuncio();

		String titulo = dadosAnuncio.get("titulo").toString();
		String descricao = dadosAnuncio.get("descricao").toString();
		Optional<EstadoAnuncioBase> estadoAnuncio = estadoAnuncioRepository.findById(Long.parseLong("1"));

		obj.setTitulo(titulo);
		obj.setDescricao(descricao);
		obj.setEstadoAnuncio(estadoAnuncio.get());

		if (dadosAnuncio.get("tipoUsuario").toString().equals("juridica")){

			PessoaJuridica pj = pessoaJuridicaService.findById(Long.parseLong(dadosAnuncio.get("idUsuario").toString()));
			obj.setPessoaJuridicaAnuncio(pj);

		} else {

			PessoaFisica pf = pessoaFisicaService.findById(Long.parseLong(dadosAnuncio.get("idUsuario").toString()));
			obj.setPessoaFisicaAnuncio(pf);

		}

		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Anuncio> update(@PathVariable Long id, @RequestBody Anuncio obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/bloquear/{id}")
    public ResponseEntity<String> bloquearAnuncio(@PathVariable Long id) {
        Anuncio anuncio = service.findById(id);
        service.bloquearAnuncio(anuncio);
        return ResponseEntity.ok("Anúncio bloqueado com sucesso.");
    }

    @PostMapping("/vender/{id}")
    public ResponseEntity<String> venderAnuncio(@PathVariable Long id) {
    	Anuncio anuncio = service.findById(id);
        service.venderAnuncio(anuncio);
        return ResponseEntity.ok("Anúncio vendido com sucesso.");
    }
}

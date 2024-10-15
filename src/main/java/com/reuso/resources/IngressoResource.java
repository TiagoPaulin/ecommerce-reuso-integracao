package com.reuso.resources;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.reuso.entities.*;
import com.reuso.repositories.EventoRepository;
import com.reuso.repositories.PessoaFisicaRepository;
import com.reuso.repositories.PessoaJuridicaRepository;
import com.reuso.services.*;
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


@RestController
@RequestMapping(value = "/ingressos")
public class IngressoResource {
	
	@Autowired
	private IngressoService service;
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	@Autowired
	private PessoaFisicaService pessoaFisicaService;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private AnuncioService anuncioService;
	
	@GetMapping
	public ResponseEntity<List<Ingresso>> findAll(){
		List<Ingresso> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ingresso> finById(@PathVariable Long id){
		Ingresso obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value = "/uingresso")
	public ResponseEntity<List<Ingresso>> getIngressosUsuario(@RequestBody Map<String, Object> userInfo) {
		return ResponseEntity.ok().body(service.buscarPorUsuario(userInfo));
	}
	
	@PostMapping
	public ResponseEntity<Ingresso> insert(@RequestBody Map<String, Object> dadosIngresso){

		Ingresso obj = new Ingresso();

		String titulo = dadosIngresso.get("titulo").toString();
		String descricao = dadosIngresso.get("descricao").toString();
		int quantidade =  Integer.parseInt(dadosIngresso.get("quantidade").toString());
		float valor = Float.parseFloat(dadosIngresso.get("valor").toString());
		Long idUsuario = Long.parseLong(dadosIngresso.get("idUsuario").toString());

		Evento evento = eventoService.findById(Long.parseLong(dadosIngresso.get("evento").toString()));
		Anuncio anuncio = anuncioService.findById(Long.parseLong(dadosIngresso.get("anuncio").toString()));

		obj.setTitulo(titulo);
		obj.setDescricao(descricao);
		obj.setQuantidade(quantidade);
		obj.setValor(valor);
		obj.setEvento(evento);
		obj.setAnuncio(anuncio);

		if (dadosIngresso.get("tipoUsuario").toString().equals("juridica")){

			PessoaJuridica pj = pessoaJuridicaService.findById(idUsuario);
			obj.setPjVendedor(pj);
			obj.setPfVendedor(null);

		} else {

			PessoaFisica pf = pessoaFisicaService.findById(idUsuario);
			obj.setPfVendedor(pf);
			obj.setPjVendedor(null);

		}

		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}

	@PostMapping(value = "/search")
	public ResponseEntity<List<Ingresso>> buscar(@RequestBody Map<String, String> search) {
		String busca = search.get("search");
		List<Ingresso> list = service.buscar(busca);
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Ingresso> update(@PathVariable Long id, @RequestBody Ingresso obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}

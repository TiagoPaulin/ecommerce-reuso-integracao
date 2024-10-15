package com.reuso.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.reuso.services.TipoEventoService;
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

import com.reuso.entities.Evento;
import com.reuso.entities.TipoEvento;
import com.reuso.services.EventoService;


@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {
	
	@Autowired
	private EventoService service;

	@Autowired
	private TipoEventoService tipoEventoService;
	
	@GetMapping
	public ResponseEntity<List<Evento>> findAll(){
		List<Evento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Evento> finById(@PathVariable Long id){
		Evento obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Evento> insert(@RequestBody Map<String, Object> dadosEvento){
		String descricao = dadosEvento.get("descricao").toString();
		String data = dadosEvento.get("data").toString();
		String horario = dadosEvento.get("horario").toString();
		TipoEvento tipo = tipoEventoService.findById(Long.parseLong(dadosEvento.get("tipoEvento").toString()));
		Evento obj = new Evento(null, descricao, data, horario, tipo);
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
	public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody Evento obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}

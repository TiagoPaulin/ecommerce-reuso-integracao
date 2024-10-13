package com.reuso.resources;

import java.net.URI;
import java.util.List;
import java.util.Map;

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

import com.reuso.entities.Ingresso;
import com.reuso.services.IngressoService;


@RestController
@RequestMapping(value = "/ingressos")
public class IngressoResource {
	
	@Autowired
	private IngressoService service;
	
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
	
	@PostMapping
	public ResponseEntity<Ingresso> insert(@RequestBody Ingresso obj){
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

package com.reuso.resources;

import java.net.URI;
import java.util.List;

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

import com.reuso.entities.PessoaJuridica;
import com.reuso.services.PessoaJuridicaService;


@RestController
@RequestMapping(value = "/pjs")
public class PessoaJuridicaResource {
	
	@Autowired
	private PessoaJuridicaService service;
	
	@GetMapping
	public ResponseEntity<List<PessoaJuridica>> findAll(){
		List<PessoaJuridica> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaJuridica> finById(@PathVariable Long id){
		PessoaJuridica obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<PessoaJuridica> insert(@RequestBody PessoaJuridica obj){
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
	public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody PessoaJuridica obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}

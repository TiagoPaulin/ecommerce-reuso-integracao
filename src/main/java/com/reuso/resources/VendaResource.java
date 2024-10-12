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

import com.reuso.entities.Venda;
import com.reuso.services.VendaService;


@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {
	
	@Autowired
	private VendaService service;
	
	@GetMapping
	public ResponseEntity<List<Venda>> findAll(){
		List<Venda> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Venda> finById(@PathVariable Long id){
		Venda obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Venda> insert(@RequestBody Venda obj){
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
	public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/realizar/{id}")
    public ResponseEntity<String> realizarVenda(@PathVariable Long id) {
        Venda venda = service.findById(id);
        service.realizarVenda(venda);
        return ResponseEntity.ok("Venda realizada com sucesso.");
    }

    @PostMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelarVenda(@PathVariable Long id) {
        Venda venda = service.findById(id);
        service.cancelarVenda(venda);
        return ResponseEntity.ok("Venda cancelada com sucesso.");
    }
    
    @PostMapping("/extornar/{id}")
    public ResponseEntity<String> extornarVenda(@PathVariable Long id) {
        Venda venda = service.findById(id);
        service.extornarVenda(venda);
        return ResponseEntity.ok("Venda extornada com sucesso.");
    }
}

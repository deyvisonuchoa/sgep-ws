package br.com.sgep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.model.Setor;
import br.com.sgep.service.SetorService;

@RestController
@RequestMapping(value = "/setor")
public class SetorController {

	@Autowired
	public SetorService service;
	
	@GetMapping
	public ResponseEntity<List<Setor>> findAll(){
		List<Setor> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Setor> findById(@PathVariable Long id){
		Setor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}

package br.com.sgep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.model.Empresa;
import br.com.sgep.model.Setor;
import br.com.sgep.service.EmpresaService;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

	@Autowired
	public EmpresaService service;
	
	@GetMapping
	public ResponseEntity<List<Empresa>> findAll(){
		List<Empresa> lista = service.findAll(); 
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Empresa> findById(@PathVariable Long id){
		Empresa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}

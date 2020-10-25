package br.com.sgep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.model.Empresa;
import br.com.sgep.service.SgepService;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

	@Autowired
	public SgepService service;
	
	@GetMapping
	public ResponseEntity<List<Empresa>> recuperaEmpresas(){
		List<Empresa> lista = service.recuperaEmpresas(); 
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Empresa> recuperaEmpresaPorId(@PathVariable Long id){
		Empresa obj = service.recuperaEmpresaPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Empresa> removeEmpresa(@PathVariable Long id){
		service.removeEmpresa(id);
		return ResponseEntity.noContent().build();
	}
}

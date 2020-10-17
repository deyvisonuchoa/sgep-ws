package br.com.sgep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.model.Funcionario;
import br.com.sgep.service.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

	@Autowired
	public FuncionarioService service;
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll(){
		List<Funcionario> lista = service.findAll(); 
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Long id){
		Funcionario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}

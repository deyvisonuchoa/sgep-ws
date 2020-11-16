package br.com.sgep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.model.Setor;
import br.com.sgep.service.SgepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Setor")
@RestController
@RequestMapping(value = "/setor")
public class SetorController {

	@Autowired
	public SgepService service;
	
	@ApiOperation(value = "Recupera Lista de setores")
	@GetMapping
	public ResponseEntity<List<Setor>> recuperaSetores(){
		List<Setor> lista = service.recuperaSetores();
		return ResponseEntity.ok().body(lista);
	}
	
	@ApiOperation(value = "Recupera Setor por Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Setor> recuperaSetorPorId(@PathVariable Long id){
		Setor obj = service.recuperaSetorPorId(id);
		return ResponseEntity.ok().body(obj);
	}
}

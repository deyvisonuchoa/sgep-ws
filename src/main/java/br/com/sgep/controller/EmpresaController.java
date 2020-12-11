package br.com.sgep.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sgep.model.Empresa;
import br.com.sgep.service.SgepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Empresa")

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

	@Autowired
	public SgepService service;
	
	@ApiOperation(value = "Recuperar lista de empresas")
	@GetMapping
	public ResponseEntity<List<Empresa>> recuperaEmpresas(){
		List<Empresa> lista = service.recuperaEmpresas(); 
		return ResponseEntity.ok().body(lista);
	}
	
	@ApiOperation(value = "Recuperar empresa por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Empresa> recuperaEmpresaPorId(@PathVariable Long id){
		Empresa obj = service.recuperaEmpresaPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Cadastro Empresa ")
	@PostMapping
	public ResponseEntity<Empresa> cadastraEmpresa(@RequestBody Empresa empresa){
		Empresa obj = service.cadastraEmpresa(empresa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@ApiOperation(value = "Remover empresa")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Empresa> removeEmpresa(@PathVariable Long id){
		service.removeEmpresa(id);
		return ResponseEntity.noContent().build();
	}
}

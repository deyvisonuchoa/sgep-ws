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
	
	@ApiOperation(value = "Cadastro setor ")
	@PostMapping
	public ResponseEntity<Setor> cadastraEmpresa(@RequestBody Setor setor){
		Setor obj = service.cadastraSetor(setor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@ApiOperation(value = "Remover setor")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Setor> removeEmpresa(@PathVariable Long id){
		service.removeSetor(id);
		return ResponseEntity.noContent().build();
	}
}

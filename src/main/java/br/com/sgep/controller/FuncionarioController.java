package br.com.sgep.controller;

import java.net.URI;
import java.util.HashMap;
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

import br.com.sgep.model.Funcionario;
import br.com.sgep.model.RegJornada;
import br.com.sgep.service.SgepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Funcionario")
@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

	@Autowired
	public SgepService service;
	
	@ApiOperation(value = "Recuperar lista de fuincionarios")
	@GetMapping
	public ResponseEntity<List<Funcionario>> recuperaFuncionarios(){
		List<Funcionario> lista = service.recuperaFuncionarios(); 
		return ResponseEntity.ok().body(lista);
	}
	
	@ApiOperation(value = "Recuperar funcionario por ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> recuperaFuncionarioPorId(@PathVariable Long id){
		Funcionario obj = service.recuperaFuncionarioPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Cadastrar funcionario")
	@PostMapping
	public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario obj){
		obj = service.cadastrarFuncionario(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
	}
	
	@ApiOperation(value = "Remover Funcionario")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Map<Object,Object>> removeFuncionario(@PathVariable Long id) {
		service.removeFuncionario(id);
		Map<Object,Object> msgSucesso = new HashMap<>();
		msgSucesso.put("msg", "sucesso");
		return ResponseEntity.ok().body(msgSucesso);
	}
	
	@ApiOperation(value = "Atualizar Funcionario")
	@PutMapping(value="/{id}")
	public ResponseEntity<Funcionario> atualizaFuncionario(@PathVariable Long id, @RequestBody Funcionario obj){
		obj = service.atualizaFuncionario(id, obj);	
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Lançar Entrada")
	@PostMapping(value = "/{id}/jornada")
	public ResponseEntity<RegJornada> lancarEntrada(@PathVariable Long id){
		RegJornada registro = service.lancarEntrada(id);
		return ResponseEntity.ok().body(registro);
	}
	
	@ApiOperation(value = "Lançar Saida")
	@PutMapping(value = "/{id}/jornada")
	public ResponseEntity<RegJornada> lancarSaida(@PathVariable Long id){
		RegJornada registro = service.lancarSaida(id);
		return ResponseEntity.ok().body(registro);
	}
	
	@ApiOperation(value = "Recupera Lançamentos de entrada e saida")
	@GetMapping(value = "/{id}/lancamentos")
	public ResponseEntity<List<RegJornada>> recuperarLancamentos(@PathVariable Long id){
		List<RegJornada> listaRegistros = service.recuperaEntradas(id);
		return ResponseEntity.ok().body(listaRegistros);
	}
	
}

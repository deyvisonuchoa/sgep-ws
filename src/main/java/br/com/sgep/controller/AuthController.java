package br.com.sgep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.model.Funcionario;
import br.com.sgep.model.dto.LoginDTO;
import br.com.sgep.service.SgepService;
import br.com.sgep.service.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Login")
@RestController
public class AuthController {

	@Autowired
	SgepService service;

	@ApiOperation(value = "tela de login")
	@PostMapping(value = "/login")
	public ResponseEntity<Funcionario> autenticaLogin(@RequestBody LoginDTO form){
		Funcionario funcionario = service.autenticaLogin(form);
		if(funcionario == null)
			throw new BusinessException("Email ou senha incorretos!");

		return ResponseEntity.ok().body(funcionario);
	}

}
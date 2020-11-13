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

@RestController
public class AuthController {

	@Autowired
	SgepService service;

	@PostMapping(value = "/login")
	public ResponseEntity<Funcionario> autenticaLogin(@RequestBody LoginDTO form){
		Funcionario funcionario = service.autenticaLogin(form);
		if(funcionario == null)
			throw new BusinessException("Email ou senha incorretos!");

		return ResponseEntity.ok().body(funcionario);
	}

}
package br.com.sgep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.model.Funcionario;
import br.com.sgep.model.dto.LoginDTO;
import br.com.sgep.service.SgepService;
import br.com.sgep.service.exception.BusinessException;

@RestController
public class LoginController {
	
	@Autowired
	SgepService service;

	@RequestMapping(value = "/v1/login", method = RequestMethod.POST)
	public ResponseEntity<Funcionario> autenticaLogin(@RequestBody LoginDTO form){
		Funcionario funcionario = service.autenticaLogin(form);
		if(funcionario == null)
			throw new BusinessException("Email ou senha incorretos!");
			
		return ResponseEntity.ok().body(funcionario);
	}

}

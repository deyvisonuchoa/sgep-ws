package br.com.sgep.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgep.dto.AuthRequest;
import br.com.sgep.security.JwtTokenProvider;

@RestController
public class AuthController {
	

	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@RequestMapping(value = "/v1/auth", method = RequestMethod.POST)
	public ResponseEntity<Map<Object, Object>> createAuthenticationToken(@RequestBody AuthRequest data) throws Exception {
		try {
			String username = data.getEmail();
			String pasword = data.getSenha();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pasword));
			
			String token = "";
			
			List<String> roles = new ArrayList<>();
			roles.add("ADMIN");
			
//			if (user != null) {
				token = tokenProvider.createToken(username, roles);
//			} else {
//				throw new UsernameNotFoundException("Username " + username + " not found!");
//			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("usuario", username);
			model.put("token", token);
			return ResponseEntity.ok().body(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}
	
	

	
}

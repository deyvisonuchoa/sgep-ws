package br.com.sgep.dto;

import java.io.Serializable;

public class AuthRequest implements Serializable{
	
	private String email;
	private String senha;	
	
	public AuthRequest() {		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String matricula) {
		this.email = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

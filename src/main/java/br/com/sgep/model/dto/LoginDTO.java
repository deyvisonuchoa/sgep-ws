package br.com.sgep.model.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable{
	
	private String email;
	private String senha;	
	
	public LoginDTO() {		
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

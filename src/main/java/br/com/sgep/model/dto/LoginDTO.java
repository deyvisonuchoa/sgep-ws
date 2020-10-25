package br.com.sgep.model.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable{
	
	private String matricula;
	private String senha;	
	
	public LoginDTO() {		
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

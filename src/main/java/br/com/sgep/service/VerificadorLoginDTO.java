package br.com.sgep.service;

import java.io.Serializable;

public class VerificadorLoginDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
}

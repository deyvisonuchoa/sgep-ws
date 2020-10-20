package br.com.sgep.service.exception;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5415031645433267494L;

	
	public ObjectNotFoundException(Long id) {
		super("Objeto não encontrado para o Id = " + id);
	}
}

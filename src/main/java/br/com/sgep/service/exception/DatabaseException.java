package br.com.sgep.service.exception;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = 7330510201084384146L;
	
	public DatabaseException(String msg) {
		super(msg);
	}

}

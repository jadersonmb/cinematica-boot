package com.cinematica.exception;

/**
 * 
 * @author Jaderson Morais
 *
 */
public class EspecialidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public EspecialidadeException() {
        super();
    }

    public EspecialidadeException(String message) {
        super(message);
    }
}

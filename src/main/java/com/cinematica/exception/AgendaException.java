package com.cinematica.exception;

/**
 * AgendaException
 */
public class AgendaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public AgendaException() {
        super();
    }

    public AgendaException(String message) {
        super(message);
    }
    
}
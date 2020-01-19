package com.cinematica.exception;


/**
 * 
 * @author Jaderson Morais
 *
 */
public class CinematicaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CinematicaException() {
		super();
	}

	public CinematicaException(String message) {
		super(message);
	}
}

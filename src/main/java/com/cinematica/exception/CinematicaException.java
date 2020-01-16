package com.cinematica.exception;

public class CinematicaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CinematicaException() {
		super();
	}

	public CinematicaException(String message) {
		super(message);
	}
}

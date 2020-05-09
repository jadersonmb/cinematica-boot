package com.cinematica.exception;

public class HorarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public HorarioException() {
        super();
    }

    public HorarioException(String message) {
        super(message);
    }
}

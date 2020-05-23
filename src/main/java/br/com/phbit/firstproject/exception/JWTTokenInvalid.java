package br.com.phbit.firstproject.exception;

public class JWTTokenInvalid extends Exception {
	private static final long serialVersionUID = 358995941795348910L;

	public JWTTokenInvalid(String message) {
		super(message);
	}
}

package com.manadev.services.exceptions;

public class EmptyResultAcess extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmptyResultAcess(String msg) {
		super(msg);
	}

	public EmptyResultAcess(String msg, Throwable cause) {
		super(msg, cause);
	}

}

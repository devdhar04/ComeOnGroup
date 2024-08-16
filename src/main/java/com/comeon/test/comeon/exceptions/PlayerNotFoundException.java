package com.comeon.test.comeon.exceptions;

public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException(String message) {
		super(message);
	}
}
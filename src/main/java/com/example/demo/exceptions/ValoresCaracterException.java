package com.example.demo.exceptions;

public class ValoresCaracterException extends RuntimeException{

	public ValoresCaracterException() {
		super("Los caracteres no son validos");
	}
}

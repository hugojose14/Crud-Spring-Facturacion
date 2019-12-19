package com.example.demo.exceptions;

public class ValorException extends RuntimeException{
	
	public ValorException() {
		super("el valor no puede ser negativo ");
	}

}

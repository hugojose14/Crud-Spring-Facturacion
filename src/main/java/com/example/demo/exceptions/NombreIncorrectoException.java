package com.example.demo.exceptions;

public class NombreIncorrectoException extends RuntimeException{

	public NombreIncorrectoException() {
		super(" El nombre no es correcto ");
	}
}

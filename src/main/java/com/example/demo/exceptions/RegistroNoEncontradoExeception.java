package com.example.demo.exceptions;

public class RegistroNoEncontradoExeception extends RuntimeException{

	public RegistroNoEncontradoExeception() {
		
		super("El registro no existe");
		
	}
}

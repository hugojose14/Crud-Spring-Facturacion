package com.example.demo.shared.dominio;

import com.example.demo.exceptions.NombreIncorrectoException;

public class Nombre {

	private final String nombre;
	
	public Nombre(String nombre) {
		
		this.nombre= nombre;
		if(!nombre.matches("[A-Z].*")) throw new NombreIncorrectoException();
			
	}
	public String getNombre() {
		return nombre;
	}
}

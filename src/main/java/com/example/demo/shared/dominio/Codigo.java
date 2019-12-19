package com.example.demo.shared.dominio;

import com.example.demo.exceptions.ValoresCaracterException;

public class Codigo {
	
	private final String codigo;

	public Codigo(String codigo) {
	
		this.codigo = codigo;
		if ( codigo.length() >1000) throw new ValoresCaracterException();
	}
	public String getCodigo() {
		return codigo;
	}
}

package com.example.demo.shared.dominio;

import com.example.demo.exceptions.ValorException;

public class Cantidad {

	private final Integer cantidad;

	public Cantidad(Integer cantidad) {
		
		this.cantidad= cantidad;
		if (cantidad<1) throw new ValorException();
	}
	public Integer getCantidad() {
		return cantidad;
	}
}

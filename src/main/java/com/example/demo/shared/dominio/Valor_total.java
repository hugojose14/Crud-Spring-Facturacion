package com.example.demo.shared.dominio;

import com.example.demo.exceptions.ValorException;
import com.example.demo.exceptions.AlcanceValorException;

public class Valor_total {

	private final Double valor;
	
	public Valor_total( Double valor) {
		
		this.valor = valor;
		
		if (valor<1) {
			throw new ValorException();
			
		}

		if(valor > 9999999999.0) {
			throw new AlcanceValorException();
		}
	}

	public Double getvalor() {
		return valor;
	}
}

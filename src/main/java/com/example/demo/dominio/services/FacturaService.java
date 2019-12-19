package com.example.demo.dominio.services;

import java.util.List;

import com.example.demo.dominio.model.Factura;
import com.example.demo.shared.dominio.Id;

public interface FacturaService {
	public Factura guardar (Factura factura);
	
	public List<Factura> buscarTodos();
	
	public Factura buscarPorId (Id numero);
		
	public Factura eliminarPorId (Id numero);

}

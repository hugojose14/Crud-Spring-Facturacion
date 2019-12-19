package com.example.demo.infraestructure.repository.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.model.Factura;
import com.example.demo.dominio.services.FacturaService;
import com.example.demo.infraestructure.mapper.FacturaMapper;
import com.example.demo.infraestructure.repository.database.FacturaRepository;
import com.example.demo.shared.dominio.Id;

@Service
public class FacturaAdapter implements FacturaService{

	@Autowired
	public FacturaRepository facturaRepository;
	
	@Autowired
	public FacturaMapper facturaMapper;
	
	@Override
	public Factura guardar(Factura factura) {
		return facturaMapper.dtoDominio(facturaRepository.save(facturaMapper.dominiodto(factura)));
	}

	@Override
	public List<Factura> buscarTodos() {
		return facturaMapper.dtoDominio(facturaRepository.findAll());
	}

	@Override
	public Factura buscarPorId(Id numero) {
		return null;
		//return facturaMapper.dtoDominio(facturaRepository.findById(numero.getId()).orElseThrow(() -> new RegistroNoEncontradoExeception()));
	}

	@Override
	public Factura eliminarPorId(Id numero) {
		Factura factura = buscarPorId(numero);
		facturaRepository.deleteById(numero.getId());
		return factura;
		
	}

}

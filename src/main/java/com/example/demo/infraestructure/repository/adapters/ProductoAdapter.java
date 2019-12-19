package com.example.demo.infraestructure.repository.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.model.Producto;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.exceptions.RegistroNoEncontradoExeception;
import com.example.demo.infraestructure.mapper.ProductoMapper;
import com.example.demo.infraestructure.repository.database.ProductoRepository;
import com.example.demo.shared.dominio.Codigo;
import com.example.demo.shared.dominio.Id;

@Service
public class ProductoAdapter implements ProductoService{
	
	@Autowired
	public ProductoRepository productoRepository;

	@Autowired
	public ProductoMapper productoMapper;

	@Override
	public void guardar(Producto producto) {
		productoRepository.save(productoMapper.dominiodtoapi(producto));
		
	}

	@Override
	public List<Producto> buscarTodos() {
		return productoMapper.dtoDominioapi(productoRepository.findAll());
	}

	@Override
	public Producto buscarxId(Codigo codigo) {
		Producto producto = productoMapper
				.dtoDominioapi(productoRepository.findById(codigo.getCodigo()).orElseThrow(() -> new RegistroNoEncontradoExeception()));

		return producto;
	}

	@Override
	public List<Producto> buscarxIds(List<Codigo> codigos) {
		return productoRepository.findAllById(codigos.stream().map(codigo -> codigo.getCodigo()).collect(Collectors.toList()))
				.stream().map(producto -> productoMapper.dtoDominioapi(producto)).collect(Collectors.toList());
	}

	@Override
	public Producto borrar(Codigo codigo) {
		Producto producto = buscarxId(codigo);
		productoRepository.deleteById(codigo.getCodigo());

		return producto;
	}

	@Override
	public Producto actualizar(Producto producto, Codigo codigo) {
		Producto p = buscarxId(codigo);
		guardar(producto);
		
		return p;
	}


}

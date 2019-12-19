package com.example.demo.apliacion;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dominio.services.FacturaService;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.exceptions.RegistroNoEncontradoExeception;
import com.example.demo.infraestructure.dto.FacturaRest;
import com.example.demo.infraestructure.dto.ItemRest;
import com.example.demo.infraestructure.dto.ProductoRest;
import com.example.demo.infraestructure.mapper.FacturaMapper;
import com.example.demo.infraestructure.mapper.ProductoMapper;
import com.example.demo.shared.dominio.Codigo;
import com.example.demo.shared.dominio.Id;

public class FacturaAplication {
	private FacturaService facturaService;
	private ProductoService productoService;
	private FacturaMapper facturaMapper;
	private ProductoMapper productoMapper;
	
	public FacturaAplication(FacturaService facturaService, ProductoService productoService,
			FacturaMapper facturaMapper, ProductoMapper productoMapper) {
		this.facturaService = facturaService;
		this.productoService = productoService;
		this.facturaMapper = facturaMapper;
		this.productoMapper = productoMapper;
	}

	public Double calcularValorTotal (List<ItemRest> items) {
		Double valor_total = 0.0;
		for (ItemRest item : items) {
			item.setId(1L);
			item.setValor_total(item.getCantidad() * item.getProducto().getValor());
			valor_total += item.getValor_total();
		}
		return valor_total;
	}
	
	public List<Codigo> obtenerCodigos (List<ItemRest> items) {
		return items.stream().map(item -> new Codigo(item.getProducto().getCodigo())).collect(Collectors.toList());
	}
	
	public void cargarProductos (List<ItemRest> items) {
		List<ProductoRest> productos = productoMapper.dominiodto(productoService.buscarxIds(obtenerCodigos(items)));
		for (ItemRest item : items) {
			item.setProducto(productos.stream().filter(producto -> producto.getCodigo().equals(item.getProducto().getCodigo())).findAny().orElseThrow(() -> new RegistroNoEncontradoExeception()));
		}
	}
	
	public FacturaRest crear (FacturaRest factura) {
		factura.setNumero(1L);
		cargarProductos(factura.getItems());
		factura.setValor_total(calcularValorTotal(factura.getItems()));
		FacturaRest obtenida = facturaMapper.dominiodtoapi(facturaService.guardar(facturaMapper.dtoDominioapi(factura)));
		System.out.println("Factura -> "+obtenida);
		return obtenida;
	}
	
	public List<FacturaRest>  mostrarTodos () {
		List<FacturaRest> facturas = facturaMapper.dominiodtoapi(facturaService.buscarTodos());
		System.out.println("Facturas -> "+facturas);
		return facturas;
	}
	
	public FacturaRest mostrarUno ( Long numero) {
		FacturaRest factura = facturaMapper.dominiodtoapi(facturaService.buscarPorId(new Id(numero)));
		System.out.println("Factura -> "+factura);
		return factura;
	}
	
	public void eliminar (Long numero) {
		FacturaRest factura = facturaMapper.dominiodtoapi(facturaService.eliminarPorId(new Id(numero)));
		System.out.println("Eliminado -> "+factura);
	}
	
	

}

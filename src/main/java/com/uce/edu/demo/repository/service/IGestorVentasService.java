package com.uce.edu.demo.repository.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoCompra;

public interface IGestorVentasService {
	
	public void insertarProducto(Producto p);

	
	public void realizarVenta(List<ProductoCompra> productos, String cedulaCliente, String numVenta);

	

}

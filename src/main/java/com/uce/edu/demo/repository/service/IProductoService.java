package com.uce.edu.demo.repository.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;

public interface IProductoService {
	
	public void insertar(Producto p);

	public Producto buscarPorCodigoBarras(String codigoBarras);
	
	public List<Producto> buscarTodos();
	
	public Producto buscar(Integer id);
	
	public void eliminar(Integer id);
	
	public void actualizar(Producto p);
}

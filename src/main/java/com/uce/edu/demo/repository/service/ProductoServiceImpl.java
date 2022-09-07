package com.uce.edu.demo.repository.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{

	
	@Autowired
	private IProductoRepository iProductoRepository;
	
	@Override
	public void insertar(Producto p) {
		this.iProductoRepository.insertar(p);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Producto buscarPorCodigoBarras(String codigoBarras) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarPorCodigoBarras(codigoBarras);
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarTodos();
	}

	@Override
	public Producto buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscar(id);
	}

	@Override
	public void eliminar(Integer id) {
		this.iProductoRepository.eliminar(id);
		
	}

	@Override
	public void actualizar(Producto p) {
		this.iProductoRepository.actualizar(p);
		
	}

}

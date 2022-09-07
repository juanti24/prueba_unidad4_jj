package com.uce.edu.demo;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.service.IGestorVentasService;
import com.uce.edu.demo.repository.service.IProductoService;

@SpringBootApplication
public class PruebaU4Jj1Application implements CommandLineRunner{
	
	@Autowired
	private IGestorVentasService gestorVentasService;

	@Autowired
	private IProductoService iProductoService;

	public static void main(String[] args) {
		SpringApplication.run(PruebaU4Jj1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Producto p = new Producto();
		p.setCodigoBarras("000112");
		p.setNombre("Nestea");
		p.setCategoria("Bebidas");
		p.setPrecio(new BigDecimal(1.00));
		p.setStock(75);

		//this.gestorVentasService.insertarProducto(p);
		
		
	}

}

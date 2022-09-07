package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class IngresarProducto {

	@Autowired
	private IProductoService iProductoService;

	// GET
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Producto> lista = this.iProductoService.buscarTodos();
		modelo.addAttribute("productos", lista);
		return "vistaListaProductos";
	}

	@PostMapping("/insertar")
	public String insertarProducto(Producto producto) {
		this.iProductoService.insertar(producto);
		return "redirect:/productos/buscar";
	}

	//

	@GetMapping("/buscarUno/{idProducto}")
	public String buscarProducto(@PathVariable("idProducto}") Integer id, Model modelo) {;
		Producto p = this.iProductoService.buscar(id);
		modelo.addAttribute("productos", p);
		return "vistaProducto";
	}

	@PutMapping("/actualizar/{idProducto}")
	public String actualizarProducto(@PathVariable("idProducto") Integer id, Producto p) {
		p.setId(id);
		this.iProductoService.actualizar(p);
		return "redirect:/productos/buscar";
	}

	@DeleteMapping("/borrar/{idProducto}")
	public String borrarProducto(@PathVariable("idProducto") Integer id) {
		this.iProductoService.eliminar(id);
		return "redirect:/productos/buscar";
	}


	@GetMapping("/nuevaProducto")
	public String paginaNuevoProdcuto(Producto p) {
		return "vistaNuevoProducto";
	}

}

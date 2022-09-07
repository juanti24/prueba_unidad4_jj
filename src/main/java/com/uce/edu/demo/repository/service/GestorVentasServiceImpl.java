package com.uce.edu.demo.repository.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoCompra;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class GestorVentasServiceImpl implements IGestorVentasService {
	
	@Autowired
	private IProductoRepository iProductoRepository;

	@Autowired
	private IVentaRepository iVentaRepository;


	@Override
	@Transactional(value = TxType.REQUIRED)
	public void insertarProducto(Producto p) {
		// TODO Auto-generated method stub
		List<String> codigos = this.iProductoRepository.consultarCodigosBarras();

		if (codigos.contains(p.getCodigoBarras())) {
			Producto productoBase = this.iProductoRepository.buscarPorCodigoBarras(p.getCodigoBarras());
			productoBase.setStock(productoBase.getStock() + p.getStock());

			this.iProductoRepository.actualizar(productoBase);
		} else
			this.iProductoRepository.insertar(p);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarVenta(List<ProductoCompra> productos, String cedulaCliente, String numVenta) {
		// TODO Auto-generated method stub
		Venta v = new Venta();
		v.setNumero(numVenta);
		v.setFecha(LocalDateTime.now());
		v.setCedulaCliente(cedulaCliente);

		Function<ProductoCompra, DetalleVenta> generarDetalle = p -> {
			Producto prodBuscado = this.iProductoRepository.buscarPorCodigoBarras(p.getCodigoBarras());

			if (prodBuscado.getStock() >= p.getCantidad()) {
				prodBuscado.setStock(prodBuscado.getStock() - p.getCantidad());
				this.iProductoRepository.actualizar(prodBuscado);

				DetalleVenta detalle = new DetalleVenta();

				detalle.setCantidad(p.getCantidad());
				detalle.setPrecioUnitario(prodBuscado.getPrecio());
				detalle.setSubtotal(prodBuscado.getPrecio().multiply(new BigDecimal(detalle.getCantidad())));

				detalle.setVenta(v);
				detalle.setProducto(prodBuscado);

				return detalle;
			}

			return null;
		};

		List<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
		BigDecimal montoPagar = BigDecimal.ZERO;

		for (ProductoCompra pc : productos) {
			DetalleVenta dv = generarDetalle.apply(pc);

			if (dv == null)
				throw new RuntimeException("No hay stock para ese producto");
			else {
				detalles.add(dv);
				montoPagar = montoPagar.add(dv.getSubtotal());
			}
		}

		v.setDetalles(detalles);
		v.setTotalVenta(montoPagar);

		this.iVentaRepository.insertar(v);
	}

}

package com.example.digitalBooking.automation_back.service;

import com.example.digitalBooking.dto.*;
import com.example.digitalBooking.service.IService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional

@SpringBootTest
class ProductosTests {

	@Autowired
	IService productoService;

	@Autowired
	IService categoriaService;

	@Autowired
	IService ciudadService;


	@Test
	@Order(1)
	void creacionProducto() throws Exception {
		int totalRegistros = productoService.findAll().size();
		int numero = (int)(Math.random()*100+1);
		List<CategoriaDTO> listaCategorias = categoriaService.findAll();
		CategoriaDTO primerCatetoria = listaCategorias.get(0);

		List<CiudadDTO> listaCiudades = ciudadService.findAll();
		CiudadDTO primerCiudad = listaCiudades.get(0);

        ProductoDTO producto = new ProductoDTO("nombre "+numero, "descripcionCorta", "descripcionLarga", "Dirección",
				 100, "ubicacion", "ubicacion2", 1, "normas",
				"Salud seguridad", "Cancelación", primerCatetoria, primerCiudad, null, null, null);

		productoService.save(producto);
		int nuevoTotal = productoService.findAll().size();
		assertTrue(totalRegistros+1==nuevoTotal, "el registro no fue guardado con exito");
	}

	@Test
	@Order(2)
	void modificarProducto() {
		String nuevoNombre = "Prueba";
		ProductoDTO productoModificado = obtenerPrimerProducto();
		productoModificado.setNombre(nuevoNombre);
		productoService.update(productoModificado);
		ProductoDTO productoModificadoDespues = obtenerPrimerProducto();
		assertEquals(productoModificadoDespues.getNombre(), nuevoNombre, "el producto no fue actualizada");
	}

	@Test
	@Order(3)
	void eliminarProducto() {
		int totalRegistros = productoService.findAll().size();
		productoService.delete(obtenerPrimerProducto().getId());
		int nuevoTotal = productoService.findAll().size();
		assertTrue(totalRegistros-1==nuevoTotal, "el registro no fue eliminado con exito");
	}

	public ProductoDTO obtenerPrimerProducto(){
		List<ProductoDTO> listaProductos = productoService.findAll();
		ProductoDTO primerProducto = listaProductos.get(0);
		return primerProducto;
	}

}
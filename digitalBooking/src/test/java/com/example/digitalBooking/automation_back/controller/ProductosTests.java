package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.CategoriaController;
import com.example.digitalBooking.controller.CiudadController;
import com.example.digitalBooking.controller.ProductoController;
import com.example.digitalBooking.dto.CategoriaDTO;
import com.example.digitalBooking.dto.CiudadDTO;
import com.example.digitalBooking.dto.ProductoDTO;
import com.example.digitalBooking.service.CategoriaService;
import com.example.digitalBooking.service.CiudadService;
import com.example.digitalBooking.service.IService;
import com.example.digitalBooking.service.ProductoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
	void crearUnProducto() throws Exception {
		ProductoController product = new ProductoController((ProductoService) productoService);
		ResponseEntity response = product.saveProducto(createAProduct());
		assertEquals(200, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	//@Test
	@Order(2)
	void consultaLosProductos() {
		assertTrue(consultaTodasLosProductos().size()>0, "no hay productos creados");
	}

	//@Test
	@Order(3)
	void consultaProductoPorID() {
		ProductoController product = new ProductoController((ProductoService) productoService);
		Optional<ProductoDTO> producto = product.findProductoById(2L);
		assertNotEquals(null,producto.get().getNombre(), "no hay nombre en el producto");
		assertNotEquals(null, producto.get().getDescripcionCorta(), "no hay descripción");
		assertNotEquals(null, producto.get().getDescripcionLarga(), "no hay descripción");
		assertNotEquals(null,producto.get().getUbicacion(), "no hay ubicación");
		assertNotEquals(null, producto.get().getUbicacion2(), "no hay ubicación");
		assertNotEquals(null, producto.get().getPuntuacion(), "no hay puntuación");
		assertNotEquals(null,producto.get().getNormas(), "no hay normas");
		assertNotEquals(null, producto.get().getSaludSeguridad(), "no Salus y seguridad");
		assertNotEquals(null, producto.get().getCancelacion(), "no hay cancelación");
	}

	@Test
	@Order(4)
	void editarProducto() {
		ProductoController product = new ProductoController((ProductoService) productoService);
		ResponseEntity response = product.updateProducto(createAProduct());
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(5)
	void eliminarProducto() {
		ProductoController product = new ProductoController((ProductoService) productoService);
		ResponseEntity response = product.deleteProducto(15L);
		System.out.println("response:" +response.getStatusCode().value());
		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	List<ProductoDTO> consultaTodasLosProductos() {
		ProductoController product = new ProductoController((ProductoService) productoService);
		List<ProductoDTO>  listaProductos = product.findAllProductos();
		return listaProductos;
	}

	ProductoDTO createAProduct() {

		int numero = (int)(Math.random()*100+1);
		CategoriaController category = new CategoriaController((CategoriaService) categoriaService);
		List<CategoriaDTO>  listaCategorias = category.findAllCategorias();

		CiudadController city = new CiudadController((CiudadService) ciudadService);
		List<CiudadDTO>  listaCiudades = city.findAllCiudades();

		ProductoDTO producto = new ProductoDTO("producto "+numero, "descripcion corta", "descripcion larga", "Dirección",
				100, "ubicacion", "ubicacion2", 1, "normas",
				"Salud seguridad", "Cancelación", listaCategorias.get(0), listaCiudades.get(0), null, null, null);

		return producto;
	}

}


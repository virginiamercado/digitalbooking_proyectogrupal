package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.CategoriaController;
import com.example.digitalBooking.dto.CategoriaDTO;
import com.example.digitalBooking.service.CategoriaService;
import com.example.digitalBooking.service.IService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoriasTests {

	@Autowired
	IService categoriaService;

	@Test
	@Order(1)
	void crearUnaCategoria() throws Exception {
		CategoriaController category = new CategoriaController((CategoriaService) categoriaService);
		int numero = (int)(Math.random()*100+1);

		CategoriaDTO categoria = new CategoriaDTO("Apartamento "+numero+" estrellas", "Hotel "+numero+" estrellas", "imagen.jpg");
		ResponseEntity response = category.saveCategoria(categoria);
		assertEquals(200, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(2)
	List<CategoriaDTO> consultaTodasLasCategorias() {
		CategoriaController category = new CategoriaController((CategoriaService) categoriaService);
		List<CategoriaDTO>  listaCategorias = category.findAllCategorias();
		assertTrue(listaCategorias.size()>0, "no hay categorías creadas");
		return listaCategorias;
	}

	@Test
	@Order(3)
	void consultaCategoriaPorID() {

		CategoriaController category = new CategoriaController((CategoriaService) categoriaService);
		List<CategoriaDTO>  categorias = consultaTodasLasCategorias();
		Long id = categorias.get(0).getId();

		Optional<CategoriaDTO> categoria = category.findCategoriaById(id);
		assertEquals(id, categoria.get().getId(), "el id no corresponde al esperado");
		assertNotEquals(null,categoria.get().getTitulo(), "no hay título en la categoría");
		assertNotEquals(null, categoria.get().getDescripcion(), "no hay descripción");
		assertNotEquals(null, categoria.get().getImagen(), "no hay imagen");

	}

	@Test
	@Order(4)
	void editarCategoria() {
		CategoriaController category = new CategoriaController((CategoriaService) categoriaService);
		List<CategoriaDTO>  categoria = consultaTodasLasCategorias();
		categoria.get(0).setTitulo("Hotel 3 estrellas");
		ResponseEntity response = category.updateCategoria(categoria.get(0));
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(5)
	void eliminarCategoria() {
		CategoriaController category = new CategoriaController((CategoriaService) categoriaService);

		List<CategoriaDTO>  categoriasAntes = consultaTodasLasCategorias();
		int categoriasA = categoriasAntes.size();

		ResponseEntity response = category.deleteCategoria(categoriasAntes.get(0).getId());

		List<CategoriaDTO>  categoriasDespues = consultaTodasLasCategorias();
		int categoriasD = categoriasDespues.size();

		System.out.println("response:" +response.getStatusCode().value());

		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
		assertEquals(categoriasA-1, categoriasD, "la categoría no fue eliminada");
	}

}


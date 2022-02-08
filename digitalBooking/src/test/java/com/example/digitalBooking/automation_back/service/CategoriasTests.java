package com.example.digitalBooking.automation_back.service;

import com.example.digitalBooking.dto.*;
import com.example.digitalBooking.service.IService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CategoriasTests {

	@Autowired
	IService categoriaService;

	@Test
	@Order(1)
	void creacionCategoria() throws Exception {
		int totalRegistros = categoriaService.findAll().size();
		int numero = (int)(Math.random()*100+1);
		CategoriaDTO categoria = new CategoriaDTO("Hotel cuatro estrellas "+numero, "Hotel 4 estrellas", "imagen.jpg");
		categoriaService.save(categoria);
		int nuevoTotal = categoriaService.findAll().size();
		assertTrue(totalRegistros+1==nuevoTotal, "el registro no fue guardado con exito");
	}

	@Test
	@Order(2)
	void modificarCategoria() {
		String newTitle = "Prueba";
		CategoriaDTO cateModificada = obtenerPrimerCategoria();
		cateModificada.setTitulo(newTitle);
		categoriaService.update(cateModificada);
		CategoriaDTO cateModificadaDespues = obtenerPrimerCategoria();
		assertEquals(cateModificadaDespues.getTitulo(), newTitle, "La categoria no fue actualizada");
	}

	@Test
	@Order(3)
	void eliminarCategoria() {
		int totalRegistros = categoriaService.findAll().size();
		categoriaService.delete(obtenerPrimerCategoria().getId());
		int nuevoTotal = categoriaService.findAll().size();
		assertTrue(totalRegistros-1==nuevoTotal, "el registro no fue eliminado");
	}

	CategoriaDTO obtenerPrimerCategoria(){
		List<CategoriaDTO> listaCategorias = categoriaService.findAll();
		CategoriaDTO primerCatetoria = listaCategorias.get(0);
		return primerCatetoria;
	}

}


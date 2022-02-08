package com.example.digitalBooking.automation_back.service;

import com.example.digitalBooking.dto.ImagenDTO;
import com.example.digitalBooking.service.IService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ImagenTests {

	@Autowired
	IService imagenService;

	@Test
	@Order(1)
	void creacionImagen() throws Exception {
		int totalImagenes = imagenService.findAll().size();
		int numero = (int)(Math.random()*100+1);
		ImagenDTO imagen = new ImagenDTO("Titulo "+numero, "URL "+numero);
		imagenService.save(imagen);
		int nuevoTotal = imagenService.findAll().size();
		assertTrue(totalImagenes+1==nuevoTotal, "el registro no fue guardado con exito");
	}

	@Test
	@Order(2)
	void modificarImagen() {
		String nuevoNombre = "Prueba";
		ImagenDTO imagenModificada = obtenerPrimerImagen();
		imagenModificada.setTitulo(nuevoNombre);
		imagenService.update(imagenModificada);
		ImagenDTO ciudadModificadaDespues = obtenerPrimerImagen();
		assertEquals(ciudadModificadaDespues.getTitulo(), nuevoNombre, "La imagen no fue actualizada");
	}


	@Test
	@Order(3)
	void eliminarImagen() {
		int totalRegistros = imagenService.findAll().size();
		imagenService.delete(obtenerPrimerImagen().getId());
		int nuevoTotal = imagenService.findAll().size();
		assertTrue(totalRegistros-1==nuevoTotal, "el registro no fue eliminado");
	}

	ImagenDTO obtenerPrimerImagen(){
		List<ImagenDTO> listaImagenes = imagenService.findAll();
		ImagenDTO primerImagen = listaImagenes.get(0);
		return primerImagen;
	}
}


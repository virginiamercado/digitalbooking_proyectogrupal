package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.ImagenController;
import com.example.digitalBooking.dto.ImagenDTO;
import com.example.digitalBooking.service.IService;
import com.example.digitalBooking.service.ImagenService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Repeat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImagenesTests {

	@Autowired
	IService imagenService;

	@Test
	@Order(1)
	void crearUnaImagen() throws Exception {
		ImagenController image = new ImagenController((ImagenService) imagenService);
		int numero = (int)(Math.random()*100+1);

		ImagenDTO imagen = new ImagenDTO("nombre imagen "+numero, "URl "+numero);
		ResponseEntity response = image.saveImagen(imagen);
		assertEquals(200, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(2)
	List<ImagenDTO> consultaTodasLasImagenes() {
		ImagenController image = new ImagenController((ImagenService) imagenService);
		List<ImagenDTO>  listaImagenes = image.findAllImagenes();
		assertTrue(listaImagenes.size()>0, "no hay imagenes creadas");
		return listaImagenes;
	}

	@Test
	@Order(3)
	void consultaImagenPorID() {

		ImagenController image = new ImagenController((ImagenService) imagenService);
		List<ImagenDTO>  imagenes = consultaTodasLasImagenes();
		Long id = imagenes.get(0).getId();

		Optional<ImagenDTO> imagen = image.findImagenById(id);
		assertEquals(id, imagen.get().getId(), "el id no corresponde al esperado");
		assertNotEquals(null,imagen.get().getTitulo(), "no hay título en la imagen");
		assertNotEquals(null, imagen.get().getUrl(), "no hay url");

	}

	@Test
	@Order(4)
	void editarImagen() {
		ImagenController image = new ImagenController((ImagenService) imagenService);
		List<ImagenDTO>  imagen = consultaTodasLasImagenes();
		imagen.get(0).setTitulo("Imagen n");
		ResponseEntity response = image.updateImagen(imagen.get(0));
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(5)
	void eliminarImagen() {
		ImagenController image = new ImagenController((ImagenService) imagenService);

		List<ImagenDTO>  imagenesAntes = consultaTodasLasImagenes();
		int imagenesA = imagenesAntes.size();

		ResponseEntity response = image.deleteImagen(imagenesAntes.get(0).getId());

		List<ImagenDTO>  imagenesDespues = consultaTodasLasImagenes();
		int imagenesD = imagenesDespues.size();

		System.out.println("response:" +response.getStatusCode().value());

		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
		assertEquals(imagenesA-1, imagenesD, "la imagen no fue eliminada");
	}

}


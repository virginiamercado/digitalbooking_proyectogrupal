package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.CiudadController;
import com.example.digitalBooking.dto.CiudadDTO;
import com.example.digitalBooking.service.CiudadService;
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
class CiudadTests {

	@Autowired
	IService ciudadService;

	@Test
	@Order(1)
	void crearUnaCiudad() throws Exception {
		CiudadController city = new CiudadController((CiudadService) ciudadService);
		int numero = (int)(Math.random()*100+1);

		CiudadDTO ciudad = new CiudadDTO("ciudad "+numero, "Pais" +numero);
		ResponseEntity response = city.saveCiudad(ciudad);
		assertEquals(200, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(2)
	void consultaLasCiudades() {
		assertTrue(consultaTodasLasCiudades().size()>0, "no hay ciudades creadas");
	}

	@Test
	@Order(3)
	void consultaCiudadesPorID() {

		CiudadController city = new CiudadController((CiudadService) ciudadService);
		List<CiudadDTO>  ciudades = consultaTodasLasCiudades();
		Long id = ciudades.get(0).getId();

		Optional<CiudadDTO> ciudad = city.findCiudadById(id);
		assertEquals(id, ciudad.get().getId(), "el id no corresponde al esperado");
		assertNotEquals(null,ciudad.get().getNombre(), "no hay nombre para la ciudad");
		assertNotEquals(null, ciudad.get().getNombre_pais(), "no hay nombre para el pais");
	}

	@Test
	@Order(4)
	void editarCiudad() {
		CiudadController city = new CiudadController((CiudadService) ciudadService);
		List<CiudadDTO>  ciudad = consultaTodasLasCiudades();
		ciudad.get(0).setNombre("Ciudad n");
		ResponseEntity response = city.updateCiudad(ciudad.get(0));
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(5)
	void eliminarCiudad() {
		CiudadController city = new CiudadController((CiudadService) ciudadService);

		List<CiudadDTO>  ciudadesAntes = consultaTodasLasCiudades();
		int ciudadesA = ciudadesAntes.size();

		ResponseEntity response = city.deleteCiudad(ciudadesAntes.get(0).getId());

		List<CiudadDTO>  ciudadesDespues = consultaTodasLasCiudades();
		int ciudadesD = ciudadesDespues.size();

		System.out.println("response:" +response.getStatusCode().value());

		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
		assertEquals(ciudadesA-1, ciudadesD, "la ciudad no fue eliminada");
	}

	List<CiudadDTO> consultaTodasLasCiudades() {
		CiudadController city = new CiudadController((CiudadService) ciudadService);
		List<CiudadDTO>  listaCiudades = city.findAllCiudades();
		return listaCiudades;
	}

}


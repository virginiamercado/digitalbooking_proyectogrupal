package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.CaracteristicaController;
import com.example.digitalBooking.dto.CaracteristicaDTO;
import com.example.digitalBooking.service.CaracteristicaService;
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
class CaracteristicasTests {

	@Autowired
	IService caracteristicaService;

	@Test
	@Order(1)
	void crearUnaCaracteristica() throws Exception {
		CaracteristicaController caracteristic = new CaracteristicaController((CaracteristicaService) caracteristicaService);
		int numero = (int)(Math.random()*100+1);

		CaracteristicaDTO caracteristica = new CaracteristicaDTO("Nombre caracteristica " +numero, "Icono");
		ResponseEntity response = caracteristic.saveCaracteristica(caracteristica);
		assertEquals(200, response.getStatusCode().value(),
				"el código de respuesta no es el esperado");
	}

	@Test
	@Order(2)
	List<CaracteristicaDTO> consultaTodasLasCaracteristicas() {
		CaracteristicaController caracteristic = new CaracteristicaController((CaracteristicaService) caracteristicaService);
		List<CaracteristicaDTO>  listaCaracteristicas = caracteristic.findAllCaracteristicas();
		assertTrue(listaCaracteristicas.size()>0, "no hay caracteristicas creadas");
		return listaCaracteristicas;
	}

	@Test
	@Order(3)
	void consultaCaracteristicaPorID() {

		CaracteristicaController caracteristic = new CaracteristicaController((CaracteristicaService) caracteristicaService);
		List<CaracteristicaDTO>  caracteristicas = consultaTodasLasCaracteristicas();
		Long id = caracteristicas.get(0).getId();

		Optional<CaracteristicaDTO> caracteristica = caracteristic.findCaracteristicaById(id);
		assertEquals(id, caracteristica.get().getId(), "el id no corresponde al esperado");
		assertNotEquals(null,caracteristica.get().getNombre(),
				"no hay nombre para la caracteristica");
		assertNotEquals(null, caracteristica.get().getIcono(),
				"no hay Icono");
	}

	@Test
	@Order(4)
	void editarCaracteristica() {
		CaracteristicaController caracteristic = new CaracteristicaController((CaracteristicaService) caracteristicaService);
		List<CaracteristicaDTO>  caracteristica = consultaTodasLasCaracteristicas();
		caracteristica.get(0).setNombre("nombre caracteristica 2");
		ResponseEntity response = caracteristic.updateCaracteristica(caracteristica.get(0));
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(5)
	void eliminarCaracteristica() {
		CaracteristicaController caracteristic = new CaracteristicaController((CaracteristicaService) caracteristicaService);

		List<CaracteristicaDTO>  caracteristicaAntes = consultaTodasLasCaracteristicas();
		int caracteristicasA = caracteristicaAntes.size();

		ResponseEntity response = caracteristic.deleteCaracteristica(caracteristicaAntes.get(0).getId());

		List<CaracteristicaDTO>  caracteristicaDespues = consultaTodasLasCaracteristicas();
		int caracteristicasD = caracteristicaDespues.size();

		System.out.println("response:" +response.getStatusCode().value());

		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
		assertEquals(caracteristicasA-1, caracteristicasD, "la caracteristica no fue eliminada");
	}

}


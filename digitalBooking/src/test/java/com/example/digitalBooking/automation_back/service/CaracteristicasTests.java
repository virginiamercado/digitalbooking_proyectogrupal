package com.example.digitalBooking.automation_back.service;

import com.example.digitalBooking.dto.CaracteristicaDTO;
import com.example.digitalBooking.service.IService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CaracteristicasTests {

	@Autowired
	IService caracteristicaService;

	@Test
	@Order(1)
	void creacionCaracteristica() throws Exception {
		int totalRegistros = caracteristicaService.findAll().size();
		int numero = (int)(Math.random()*100+1);
		CaracteristicaDTO caracteristica = new CaracteristicaDTO("Pileta "+numero, "icono pileta");
		caracteristicaService.save(caracteristica);
		int nuevoTotal = caracteristicaService.findAll().size();
		assertTrue(totalRegistros+1==nuevoTotal, "el registro no fue guardado");
	}

	@Test
	@Order(2)
	void modificarCaracteristica() {
		String nuevoTitulo = "Aire acondicionado";
		CaracteristicaDTO caractModificada = obtenerPrimerCaracteristica();
		caractModificada.setNombre(nuevoTitulo);
		caracteristicaService.update(caractModificada);
		CaracteristicaDTO caractModificadaDespues = obtenerPrimerCaracteristica();
		assertEquals(caractModificadaDespues.getNombre(), nuevoTitulo,
				"La caracteristica no fue actualizada");
	}

	@Test
	@Order(3)
	void eliminarCaracteristica() {
		int totalRegistros = caracteristicaService.findAll().size();
		caracteristicaService.delete(obtenerPrimerCaracteristica().getId());
		int nuevoTotal = caracteristicaService.findAll().size();
		assertTrue(totalRegistros-1==nuevoTotal, "el registro no fue eliminado");
	}

	CaracteristicaDTO obtenerPrimerCaracteristica(){
		List<CaracteristicaDTO> listaCategorias = caracteristicaService.findAll();
		CaracteristicaDTO primerCatetoria = listaCategorias.get(0);
		return primerCatetoria;
	}
}


package com.example.digitalBooking.automation_back.service;

import com.example.digitalBooking.dto.CiudadDTO;
import com.example.digitalBooking.service.IService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CiudadTests {

	@Autowired
	IService ciudadService;

	@Test
	@Order(1)
	void creacionCiudad() throws Exception {
		int totalRegistros = ciudadService.findAll().size();
		int numero = (int)(Math.random()*100+1);
		CiudadDTO ciudad = new CiudadDTO("Nombre ciudad "+numero, "Nombre país");
		ciudadService.save(ciudad);
		int nuevoTotal = ciudadService.findAll().size();
		assertTrue(totalRegistros+1==nuevoTotal, "el registro no fue guardado con exito");
	}

	@Test
	@Order(2)
	void modificarCategoria() {
		String nuevoNombre = "Prueba";
		CiudadDTO ciudadModificada = obtenerPrimerCiudad();
		ciudadModificada.setNombre(nuevoNombre);
		ciudadService.update(ciudadModificada);
		CiudadDTO ciudadModificadaDespues = obtenerPrimerCiudad();
		assertEquals(ciudadModificadaDespues.getNombre(), nuevoNombre, "La ciudad no fue actualizada");
	}

	@Test
	@Order(3)
	void eliminarCiudad() {
		int totalRegistros = ciudadService.findAll().size();
		ciudadService.delete(obtenerPrimerCiudad().getId());
		int nuevoTotal = ciudadService.findAll().size();
		assertTrue(totalRegistros-1==nuevoTotal, "el registro no fue eliminado");
	}

	CiudadDTO obtenerPrimerCiudad(){
		List<CiudadDTO> listaCiudades = ciudadService.findAll();
		CiudadDTO primerCiudad = listaCiudades.get(0);
		return primerCiudad;
	}
}


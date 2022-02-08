package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.RolController;
import com.example.digitalBooking.dto.RolDTO;
import com.example.digitalBooking.service.IService;
import com.example.digitalBooking.service.RolService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RolTests {

	@Autowired
	IService rolService;

	@Test
	@Order(1)
	void crearRol() throws Exception {
		RolController rol = new RolController((RolService) rolService);
		int numero = (int)(Math.random()*100+1);

		RolDTO nuevo_rol = new RolDTO("rol "+numero);

		ResponseEntity response = rol.saveRol(nuevo_rol);
		assertEquals(200, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(2)
	void consultaRoles() {
		assertTrue(consultaTodasLasCiudades().size()>0, "no hay ciudades creadas");
	}

	@Test
	@Order(3)
	void editarRol() {
		RolController rol = new RolController((RolService) rolService);
		List<RolDTO>  listaRoles = consultaTodasLasCiudades();
		listaRoles.get(0).setNombre("rol n");
		ResponseEntity response = rol.updateRol(listaRoles.get(0));
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(4)
	void eliminarRol() {
		RolController rol = new RolController((RolService) rolService);
		List<RolDTO>  listaRoles = consultaTodasLasCiudades();
		int rolesA = listaRoles.size();

		ResponseEntity response = rol.deleteRol(listaRoles.get(0).getId());

		listaRoles = consultaTodasLasCiudades();
		int rolesD = listaRoles.size();

		System.out.println("response:" +response.getStatusCode().value());

		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
		assertEquals(rolesA-1, rolesD, "el rol no fue eliminado");
	}

	List<RolDTO> consultaTodasLasCiudades() {
		RolController rol = new RolController((RolService) rolService);
		List<RolDTO>  listaRoles = rol.findAllRoles();
		return listaRoles;
	}
}


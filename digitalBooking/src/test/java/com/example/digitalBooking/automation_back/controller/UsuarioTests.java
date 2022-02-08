package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.RolController;
import com.example.digitalBooking.controller.UsuarioController;
import com.example.digitalBooking.dto.RolDTO;
import com.example.digitalBooking.dto.UsuarioDTO;
import com.example.digitalBooking.service.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioTests {

	@Autowired
	IService usuarioService;

	@Autowired
	IService rolService;

	@Test
	@Order(1)
	void crearUnUsuario() throws Exception {
		BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
		UsuarioController user = new UsuarioController((UsuarioService) usuarioService, bCryptPasswordEncoder);

		RolController rol = new RolController((RolService) rolService);
		List<RolDTO>  roles = rol.findAllRoles();
		int numero = (int)(Math.random()*100+1);

		UsuarioDTO usuario = new UsuarioDTO("nombre "+numero, "apellido", "fechaNacimiento", "paisNacimiento", "email"+numero+"@gmail.com",
				"password", roles.get(0), null, null);

		ResponseEntity response = user.saveUsuario(usuario);
		assertEquals(201, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(2)
	void consultaLosUsuarios() {
		assertTrue(consultaTodasLosUsuarios().size()>0, "no hay usuarios creados");
	}

	@Test
	@Order(3)
	void editarUsuario() {
		BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
		UsuarioController user = new UsuarioController((UsuarioService) usuarioService, bCryptPasswordEncoder);
		List<UsuarioDTO>  listaUsuarios = consultaTodasLosUsuarios();
		listaUsuarios.get(0).setNombre("usuario n");
		ResponseEntity response = user.updateUsuario(listaUsuarios.get(0));
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(4)
	void eliminarUsuario() {
		BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
		UsuarioController user = new UsuarioController((UsuarioService) usuarioService, bCryptPasswordEncoder);
		List<UsuarioDTO>  listaUsuarios = consultaTodasLosUsuarios();
		int usuariosA = listaUsuarios.size();

		ResponseEntity response = user.deleteUsuario(listaUsuarios.get(0).getId());

		listaUsuarios = consultaTodasLosUsuarios();
		int usuariosD = listaUsuarios.size();

		System.out.println("response:" +response.getStatusCode().value());

		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
		assertEquals(usuariosA-1, usuariosD, "el usuario no fue eliminado");
	}

	List<UsuarioDTO> consultaTodasLosUsuarios() {
		BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
		UsuarioController user = new UsuarioController((UsuarioService) usuarioService, bCryptPasswordEncoder);
		List<UsuarioDTO>  listaUsuarios = user.findAllUsuarios();
		return listaUsuarios;
	}
}


package com.example.digitalBooking.automation_back.controller;

import com.example.digitalBooking.controller.*;
import com.example.digitalBooking.dto.*;
import com.example.digitalBooking.service.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservasTests {

	@Autowired
	IService reservaService;

	@Autowired
	IService categoriaService;

	@Autowired
	IService ciudadService;

	@Autowired
	IService usuarioService;

	@Test
	@Order(1)
	void crearUnaReserva() throws Exception {
		ReservaController Reserv = new ReservaController((ReservaService) reservaService);
		int numero = (int)(Math.random()*100+1);

		CategoriaController category = new CategoriaController((CategoriaService) categoriaService);
		List<CategoriaDTO>  listaCategorias = category.findAllCategorias();

		CiudadController city = new CiudadController((CiudadService) ciudadService);
		List<CiudadDTO>  listaCiudades = city.findAllCiudades();

		BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
		UsuarioController user = new UsuarioController((UsuarioService) usuarioService, bCryptPasswordEncoder);
		List<UsuarioDTO>  listaUsuarios = user.findAllUsuarios();

		ProductoDTO producto = new ProductoDTO("producto "+numero, "descripcion corta", "descripcion larga", "Dirección",
				100, "ubicacion", "ubicacion2", 1, "normas",
				"Salud seguridad", "Cancelación", listaCategorias.get(0), listaCiudades.get(0), null, null, null);

		ReservaDTO reserva = new ReservaDTO("15:00", LocalDate.parse("2022-01-01"), LocalDate.parse("2022-01-10"), listaUsuarios.get(0), producto);
		ResponseEntity response = Reserv.saveReserva(reserva);
		assertEquals(200, response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(2)
	void consultaLasReservas() {
		assertTrue(consultaTodasLasReservas().size()>0, "no hay reservas creadas");
	}

	@Test
	@Order(3)
	void consultaReservaPorID() {

		ReservaController reserv = new ReservaController((ReservaService) reservaService);
		List<ReservaDTO>  reservas = consultaTodasLasReservas();
		Long id = reservas.get(0).getId();

		Optional<ReservaDTO> reserva = reserv.findReservaById(id);
		assertEquals(id, reserva.get().getId(), "el id no corresponde al esperado");
		assertNotEquals(null,reserva.get().getHoraLlegada(), "no hay hora de llegada");
		assertNotEquals(null, reserva.get().getFechaLlegada(), "no hay fecha de llegada");
		assertNotEquals(null, reserva.get().getFechaPartida(), "no hay fecha de partida");
		assertNotEquals(null, reserva.get().getUsuario(), "no hay usuario");
		assertNotEquals(null, reserva.get().getProducto(), "no hay producto");

	}

	@Test
	@Order(4)
	void editarReserva() {
		ReservaController reserv = new ReservaController((ReservaService) reservaService);
		List<ReservaDTO>  reserva = consultaTodasLasReservas();
		reserva.get(0).setHoraLlegada("16:00");
		ResponseEntity response = reserv.updateReserva(reserva.get(0));
		assertEquals(200,response.getStatusCode().value(), "el código de respuesta no es el esperado");
	}

	@Test
	@Order(5)
	void eliminarReserva() {
		ReservaController reserv = new ReservaController((ReservaService) reservaService);

		List<ReservaDTO>  reservasAntes = consultaTodasLasReservas();
		int reservasA = reservasAntes.size();

		ResponseEntity response = reserv.deleteReserva(reservasAntes.get(0).getId());

		List<ReservaDTO>  reservasDespues = consultaTodasLasReservas();
		int reservasD = reservasDespues.size();

		System.out.println("response:" +response.getStatusCode().value());

		assertEquals(204, response.getStatusCode().value(), "el código de respuesta no es el esperado");
		assertEquals(reservasA-1, reservasD, "la reserva no fue eliminada");
	}

	List<ReservaDTO> consultaTodasLasReservas() {
		ReservaController reserv = new ReservaController((ReservaService) reservaService);
		List<ReservaDTO>  listaReservas = reserv.findAllReservas();
		return listaReservas;
	}

}


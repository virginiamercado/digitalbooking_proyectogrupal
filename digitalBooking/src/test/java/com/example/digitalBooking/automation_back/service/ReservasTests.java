package com.example.digitalBooking.automation_back.service;

import com.example.digitalBooking.dto.ProductoDTO;
import com.example.digitalBooking.dto.ReservaDTO;
import com.example.digitalBooking.service.IService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional

@SpringBootTest
public class ReservasTests {

    @Autowired
    IService reservaService;

    @Autowired
    IService productoService;

    @Test
    @Order(1)
    void creacionReserva () throws Exception {
        int totalRegistros = reservaService.findAll().size();

        List<ProductoDTO> listaProductos = productoService.findAll();
        ProductoDTO primerProducto = listaProductos.get(0);

        ReservaDTO reserva = new ReservaDTO("15:00", LocalDate.parse("2022-01-01"), LocalDate.parse("2022-01-10"),null, primerProducto);
        reservaService.save(reserva);
        int nuevoTotal = reservaService.findAll().size();
        assertTrue(totalRegistros+1==nuevoTotal, "el registro no fue guardado con exito");
    }

    @Test
    @Order(2)
    void modificarReserva() {
        String nuevaHoraLlegada= "Prueba";
        ReservaDTO reservaModificada = obtenerPrimeraReserva();
        reservaModificada.setHoraLlegada(nuevaHoraLlegada);
        reservaService.update(reservaModificada );
        ReservaDTO reservaModificadaDespues = obtenerPrimeraReserva();
        assertEquals(reservaModificadaDespues.getHoraLlegada(), nuevaHoraLlegada, "la reserva no fue actualizada");
    }

    @Test
    @Order(3)
    void eliminarReserva() {
        int totalRegistros = reservaService.findAll().size();
        reservaService.delete(obtenerPrimeraReserva().getId());
        int nuevoTotal = reservaService.findAll().size();
        assertTrue(totalRegistros-1==nuevoTotal, "el registro no fue eliminado con exito");
    }

    ReservaDTO obtenerPrimeraReserva(){
        List<ReservaDTO> listaReservas = reservaService.findAll();
        ReservaDTO primeraReserva = listaReservas.get(0);
        return primeraReserva;
    }

}



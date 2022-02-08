package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.ReservaDTO;
import com.example.digitalBooking.model.Reserva;
import com.example.digitalBooking.service.ReservaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/reservas")
public class ReservaController {
    ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @ApiOperation(value = "Registro de una nueva reserva")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PostMapping("/registro")
    public ResponseEntity saveReserva(@RequestBody ReservaDTO reserva) throws Exception {
        return new ResponseEntity(reservaService.save(reserva), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar reserva por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("{id}")
    public Optional<ReservaDTO> findReservaById(@PathVariable Long id) {
        return reservaService.findById(id);
    }

    @ApiOperation(value = "Listar todas las reservas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping()
    public List<ReservaDTO> findAllReservas() {
        return reservaService.findAll();
    }

    @ApiOperation(value = "Modificar una reserva")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/modificar")
    public ResponseEntity updateReserva(@RequestBody ReservaDTO reserva) {
        return new ResponseEntity(reservaService.update(reserva), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar una reserva")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteReserva(@PathVariable Long id) {
        reservaService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Listar todas las reservas por id de producto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/producto/{id}")
    public List<ReservaDTO> findReservasByProductoId(@PathVariable Long id) {
        return reservaService.findReservasByProductoId(id);
    }

    @ApiOperation(value = "Listar todas las reservas por id de usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/usuario/{id}")
    public List<ReservaDTO> findReservasByUsuarioId(@PathVariable Long id) {
        return reservaService.findReservasByUsuarioId(id);
    }

}

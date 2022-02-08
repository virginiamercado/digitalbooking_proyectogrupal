package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.CaracteristicaDTO;
import com.example.digitalBooking.service.CaracteristicaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    private CaracteristicaService caracteristicaService;

    @Autowired
    public CaracteristicaController(CaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @ApiOperation(value = "Registro de una nueva caracteristica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PostMapping("/registro")
    public ResponseEntity saveCaracteristica(@RequestBody CaracteristicaDTO caracteristica) throws Exception {
        return new ResponseEntity(caracteristicaService.save(caracteristica), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar caracteristica por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("{id}")
    public Optional<CaracteristicaDTO> findCaracteristicaById(@PathVariable Long id) {
        return caracteristicaService.findById(id);
    }

    @ApiOperation(value = "Listar todas las caracteristicas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping()
    public List<CaracteristicaDTO> findAllCaracteristicas() {
        return caracteristicaService.findAll();
    }

    @ApiOperation(value = "Modificar una caracteristica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/modificar")
    public ResponseEntity updateCaracteristica(@RequestBody CaracteristicaDTO caracteristicaDTO) {
        return new ResponseEntity(caracteristicaService.update(caracteristicaDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar una caracteristica")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteCaracteristica(@PathVariable Long id) {
        caracteristicaService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

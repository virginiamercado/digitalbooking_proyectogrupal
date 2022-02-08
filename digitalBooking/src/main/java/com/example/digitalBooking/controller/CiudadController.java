package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.CiudadDTO;
import com.example.digitalBooking.service.CiudadService;
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
@RequestMapping("/ciudades")
public class CiudadController {

    private CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @ApiOperation(value = "Registro de una nueva ciudad")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping("/registro")
    public ResponseEntity saveCiudad(@RequestBody CiudadDTO ciudad) throws Exception {
        return new ResponseEntity(ciudadService.save(ciudad), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar ciudad por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("{id}")
    public Optional<CiudadDTO> findCiudadById(@PathVariable Long id) {
        return ciudadService.findById(id);
    }

    @ApiOperation(value = "Listar todas las ciudades")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping()
    public List<CiudadDTO> findAllCiudades() {
        return ciudadService.findAll();
    }


    @ApiOperation(value = "Modificar una ciudad")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping("/modificar")
    public ResponseEntity updateCiudad(@RequestBody CiudadDTO ciudadDTO) {
        return new ResponseEntity(ciudadService.update(ciudadDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar una ciudad")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteCiudad(@PathVariable Long id) {
        ciudadService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Listar ciudades por nombre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/nombre")
    public List<CiudadDTO> findCiudadesByNombre(String nombre) {
        return ciudadService.findCiudadesByNombre(nombre);
    }


}

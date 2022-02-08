package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.RolDTO;
import com.example.digitalBooking.service.RolService;
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
@RequestMapping("/roles")
public class RolController {
    RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @ApiOperation(value = "Registro de un nuevo rol")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PostMapping("/registro")
    public ResponseEntity saveRol(@RequestBody RolDTO rol) throws Exception {
        return new ResponseEntity(rolService.save(rol), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar rol por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("{id}")
    public Optional<RolDTO> findRolById(@PathVariable Long id) {
        return rolService.findById(id);
    }

    @ApiOperation(value = "Listar todos los roles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping()
    public List<RolDTO> findAllRoles() {
        return rolService.findAll();
    }

    @ApiOperation(value = "Modificar un rol")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/modificar")
    public ResponseEntity updateRol(@RequestBody RolDTO rol) {
        return new ResponseEntity(rolService.update(rol), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar un rol")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteRol(@PathVariable Long id) {
        rolService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

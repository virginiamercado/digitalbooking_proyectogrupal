package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.*;
import com.example.digitalBooking.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @ApiOperation(value = "Registro de una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PostMapping("/registro")
    public ResponseEntity saveCategoria(@RequestBody CategoriaDTO categoria) throws Exception {
        return new ResponseEntity(categoriaService.save(categoria), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar categoria por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("{id}")
    public Optional<CategoriaDTO> findCategoriaById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @ApiOperation(value = "Listar todas las categorías")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping()
    public List<CategoriaDTO> findAllCategorias() {
        return categoriaService.findAll();
    }

    @ApiOperation(value = "Modificar una categoría")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/modificar")
    public ResponseEntity updateCategoria(@RequestBody CategoriaDTO categoria) {
        return new ResponseEntity(categoriaService.update(categoria), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar una categoría")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteCategoria(@PathVariable Long id) {
        categoriaService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

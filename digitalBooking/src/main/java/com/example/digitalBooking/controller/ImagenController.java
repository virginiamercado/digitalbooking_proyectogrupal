package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.ImagenDTO;
import com.example.digitalBooking.service.ImagenService;
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
@RequestMapping("/imagenes")
public class ImagenController {

    private ImagenService imagenService;

    @Autowired
    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @ApiOperation(value = "Registro de una nueva imagen")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping("/registro")
    public ResponseEntity saveImagen(@RequestBody ImagenDTO imagenDTO) throws Exception {
        return new ResponseEntity(imagenService.save(imagenDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar imagen por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("{id}")
    public Optional<ImagenDTO> findImagenById(@PathVariable Long id) {
        return imagenService.findById(id) ;
    }

    @ApiOperation(value = "Listar todas las imagenes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping()
    public List<ImagenDTO> findAllImagenes() {
        return imagenService.findAll();
    }

    @ApiOperation(value = "Modificar una imagen")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping("/modificar")
    public ResponseEntity updateImagen(@RequestBody ImagenDTO imagenDTO) {
        return new ResponseEntity(imagenService.update(imagenDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar una imagen")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminada con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteImagen(@PathVariable Long id) {
        imagenService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

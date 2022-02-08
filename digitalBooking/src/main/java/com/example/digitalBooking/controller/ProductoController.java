package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.*;
import com.example.digitalBooking.model.*;
import com.example.digitalBooking.repository.impl.CaracteristicaRepository;
import com.example.digitalBooking.repository.impl.CategoriaRepository;
import com.example.digitalBooking.repository.impl.CiudadRepository;
import com.example.digitalBooking.repository.impl.ProductoRepository;
import com.example.digitalBooking.service.CaracteristicaService;
import com.example.digitalBooking.service.CategoriaService;
import com.example.digitalBooking.service.CiudadService;
import com.example.digitalBooking.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoController {
    private ProductoService productoService;


    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @ApiOperation(value = "Registro de un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PostMapping("/registro")
    public ResponseEntity saveProducto(@RequestBody ProductoDTO producto) throws Exception {
        return new ResponseEntity(productoService.save(producto), HttpStatus.OK);
    }



    @ApiOperation(value = "Buscar producto por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/{id}")
    public Optional<ProductoDTO> findProductoById(@PathVariable Long id) {
        return productoService.findById(id);
    }

    @ApiOperation(value = "Listar todos los productos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping()
    public List<ProductoDTO> findAllProductos() {
        return productoService.findAll();
    }

    @ApiOperation(value = "Modificar un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/modificar")
    public ResponseEntity updateProducto(@RequestBody ProductoDTO productoDTO) {
    return new ResponseEntity(productoService.update(productoDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteProducto(@PathVariable Long id) {
        productoService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Filtrar productos por ciudad")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/ciudad/{ciudad}")
    public List<ProductoDTO> findProductosByCiudad(@PathVariable String ciudad) {
        return productoService.findProductosByCiudad(ciudad);
    }

    @ApiOperation(value = "Filtrar productos por categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/categoria/{categoria}")
    public List<ProductoDTO> findProductosByCategoria(@PathVariable String categoria) {
        return productoService.findProductosByCategoria(categoria);
    }

    //trae los productos reservados por fechas
    @ApiOperation(value = "Filtrar productos reservados por fechas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/fechas/reservados")
    public List<ProductoDTO> findProductosReservadosByFechas(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaIngreso,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaEgreso) throws Exception {
        return productoService.findProductosReservadosByFechas(fechaIngreso, fechaEgreso);
    }

    //trae los productos disponibles por fechas
    @ApiOperation(value = "Filtrar productos disponibles por fechas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/fechas/disponibles")
    public List<ProductoDTO> findProductosDisponiblesByFechas(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaIngreso,
                                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaEgreso) throws Exception {
        return productoService.findProductosDisponiblesByFechas(fechaIngreso, fechaEgreso);
    }

    //trae los productos reservados por fechas y ciudad
    @ApiOperation(value = "Filtrar productos reservados por fechas y ciudad")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/fechas/ciudad/reservados")
    public List<ProductoDTO> findProductosReservadosByFechasAndCiudad(@RequestParam @DateTimeFormat(pattern = "yyyy" +
            "-MM-dd") LocalDate fechaIngreso,
                                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaEgreso, @RequestParam String ciudad) throws Exception {
        return productoService.findProductosReservadosByFechasAndCiudad(fechaIngreso, fechaEgreso, ciudad);
    }

    //trae los productos disponibles por fechas y ciudad
    @ApiOperation(value = "Filtrar productos disponibles por fechas y ciudad")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/fechas/ciudad/disponibles")
    public List<ProductoDTO> findProductosDisponiblesByFechasAndCiudad(@RequestParam @DateTimeFormat(pattern = "yyyy" +
            "-MM-dd") LocalDate fechaIngreso,
                                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaEgreso, @RequestParam String ciudad) throws Exception {
        return productoService.findProductosDisponiblesByFechasAndCiudad(fechaIngreso, fechaEgreso, ciudad);
    }



}

package com.example.digitalBooking.service;

import com.example.digitalBooking.dto.ProductoDTO;
import com.example.digitalBooking.model.Producto;
import com.example.digitalBooking.repository.impl.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IService<ProductoDTO>{

    private ProductoRepository productoRepository;
    private ObjectMapper mapper;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, ObjectMapper mapper) {
        this.productoRepository = productoRepository;
        this.mapper = mapper;
    }


    @Override
    public ProductoDTO save(ProductoDTO producto) throws Exception{
        if (productoRepository.findByNombreYUbicacion(producto.getNombre(), producto.getUbicacion()).isPresent()) {
            throw new Exception("Ya existe un producto con este nombre y ubicacion!");
        } else {
            productoRepository.save(mapper.convertValue(producto, Producto.class));
        }
        return producto;
    }

    @Override
    public Optional<ProductoDTO> findById(Long id) {
        ProductoDTO productoDTO = null;
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()) {
            productoDTO = mapper.convertValue(producto, ProductoDTO.class);
        }
        return Optional.ofNullable(productoDTO);
    }

    @Override
    public List<ProductoDTO> findAll() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for(Producto prod : productos) {
            productosDTO.add(mapper.convertValue(prod, ProductoDTO.class));
        }
        return productosDTO;
    }

    @Override
    public ProductoDTO update(ProductoDTO productoDTO) {
        productoRepository.save(mapper.convertValue(productoDTO, Producto.class));
        return productoDTO;
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);

    }

    public List<ProductoDTO> findProductosByCategoria(String tituloCategoria) {
        List<Producto> productos = productoRepository.findProductosByCategoria(tituloCategoria);
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for(Producto producto : productos) {
            productosDTO.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return productosDTO;
    }

    public List<ProductoDTO> findProductosByCiudad(String nombreCiudad) {
        List<Producto> productos = productoRepository.findProductosByCiudad(nombreCiudad);
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for(Producto producto : productos) {
            productosDTO.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return productosDTO;
    }

    //trae los productos reservados por fechas
    public List<ProductoDTO> findProductosReservadosByFechas(LocalDate fechaIngreso, LocalDate fechaEgreso) throws Exception{
        if(fechaEgreso.isBefore(fechaIngreso)) {
            throw new Exception("La fecha de salida no puede ser anterior a la fecha de ingreso");
        } else {
            List<Producto> productos = productoRepository.findProductosReservadosByFechas(fechaIngreso, fechaEgreso);
            List<ProductoDTO> productosDTO = new ArrayList<>();
            for (Producto producto : productos) {
                productosDTO.add(mapper.convertValue(producto, ProductoDTO.class));
            }
            return productosDTO;
        }
    }

    //trae los productos disponibles por fechas
    public List<ProductoDTO> findProductosDisponiblesByFechas(LocalDate fechaIngreso, LocalDate fechaEgreso) throws Exception{
        if(fechaEgreso.isBefore(fechaIngreso)) {
            throw new Exception("La fecha de salida no puede ser anterior a la fecha de ingreso");
        } else {
            List<Producto> productos = productoRepository.findAll();
            List<Producto> productosReservados = productoRepository.findProductosReservadosByFechas(fechaIngreso,
                    fechaEgreso);

            productos.removeIf(productosReservados::contains);

            List<ProductoDTO> productosDTO = new ArrayList<>();
            for (Producto producto : productos) {
                productosDTO.add(mapper.convertValue(producto, ProductoDTO.class));
            }
            return productosDTO;
        }
    }

    //trae los productos reservados por fechas y ciudad
    public List<ProductoDTO> findProductosReservadosByFechasAndCiudad(LocalDate fechaIngreso, LocalDate fechaEgreso,
                                                                      String ciudad) throws Exception{
        if(fechaEgreso.isBefore(fechaIngreso)) {
            throw new Exception("La fecha de salida no puede ser anterior a la fecha de ingreso");
        } else {
            List<Producto> productos = productoRepository.findProductosReservadosByFechasAndCiudad(fechaIngreso, fechaEgreso,
                    ciudad);
            List<ProductoDTO> productosDTO = new ArrayList<>();
            for (Producto producto : productos) {
                productosDTO.add(mapper.convertValue(producto, ProductoDTO.class));
            }
            return productosDTO;
        }
    }

    //trae los productos disponibles por fechas y ciudad
    public List<ProductoDTO> findProductosDisponiblesByFechasAndCiudad(LocalDate fechaIngreso, LocalDate fechaEgreso,
                                                                       String ciudad) throws Exception{
        if(fechaEgreso.isBefore(fechaIngreso)) {
            throw new Exception("La fecha de salida no puede ser anterior a la fecha de ingreso");
        } else {
            List<Producto> productos = productoRepository.findProductosByCiudad(ciudad);
            List<Producto> productosReservados =
                    productoRepository.findProductosReservadosByFechasAndCiudad(fechaIngreso,
                            fechaEgreso, ciudad);

            productos.removeIf(productosReservados::contains);

            List<ProductoDTO> productosDTO = new ArrayList<>();
            for (Producto producto : productos) {
                productosDTO.add(mapper.convertValue(producto, ProductoDTO.class));
            }
            return productosDTO;
        }
    }
}

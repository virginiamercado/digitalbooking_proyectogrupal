package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.Producto;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;

public class CaracteristicaDTO {
    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String nombre;

    @ApiModelProperty(position = 2)
    private String icono;

    @ApiModelProperty(hidden = true)
    private Set<Producto> productos;

    public CaracteristicaDTO() {
    }

    public CaracteristicaDTO(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
        this.productos = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }


}

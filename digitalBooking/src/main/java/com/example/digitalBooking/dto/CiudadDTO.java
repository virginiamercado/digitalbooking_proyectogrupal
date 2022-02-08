package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.Ciudad;
import com.example.digitalBooking.model.Producto;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;

public class CiudadDTO extends Ciudad {

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String nombre;

    @ApiModelProperty(position = 2)
    private String nombre_pais;

    @ApiModelProperty(hidden = true)
    Set<Producto> productos;

    public CiudadDTO() {
    }

    public CiudadDTO(String nombre, String nombre_pais) {
        this.nombre = nombre;
        this.nombre_pais = nombre_pais;
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

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }


}

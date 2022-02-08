package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.Categoria;
import com.example.digitalBooking.model.Producto;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;

public class CategoriaDTO extends Categoria {
    @ApiModelProperty(position = 0)
    Long id;

    @ApiModelProperty(position = 1)
    String titulo;

    @ApiModelProperty(position = 2)
    String descripcion;

    @ApiModelProperty(position = 3)
    String imagen;

    @ApiModelProperty(hidden = true)
    Set<Producto> productos;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String titulo, String descripcion, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.productos = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }


}

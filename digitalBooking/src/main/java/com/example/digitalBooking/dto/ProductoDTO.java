package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;

public class ProductoDTO extends Producto{

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String nombre;

    @ApiModelProperty(position = 2)
    private String descripcionCorta;

    @ApiModelProperty(position = 3)
    private String descripcionLarga;

    @ApiModelProperty(position = 4)
    private String ubicacion;

    @ApiModelProperty(position = 5)
    private String ubicacion2;

    @ApiModelProperty(position = 6)
    private Integer puntuacion;

    @ApiModelProperty(position = 7)
    private String normas;

    @ApiModelProperty(position = 8)
    private String saludSeguridad;

    @ApiModelProperty(position = 9)
    private String cancelacion;

    @ApiModelProperty(position = 10)
    private Categoria categoria;

    @ApiModelProperty(position = 11)
    private Ciudad ciudad;

    @ApiModelProperty(position = 12)
    private Set<Imagen> imagenes;

    @ApiModelProperty(position = 13)
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @ApiModelProperty(position = 14)
    private Set<Reserva> reservas = new HashSet<>();

    @ApiModelProperty(position = 15)
    private String direccion;

    @ApiModelProperty(position = 16)
    private Integer precio;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, String descripcionCorta, String descripcionLarga,
                       String direccion, Integer precio, String ubicacion, String ubicacion2, Integer puntuacion,
                       String normas, String saludSeguridad, String cancelacion, Categoria categoria, Ciudad ciudad, Set<Imagen> imagenes, Set<Caracteristica> caracteristicas, Set<Reserva> reservas) {
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
        this.direccion = direccion;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.ubicacion2 = ubicacion2;
        this.puntuacion = puntuacion;
        this.normas = normas;
        this.saludSeguridad = saludSeguridad;
        this.cancelacion = cancelacion;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.imagenes = imagenes;
        this.caracteristicas = caracteristicas;
        this.reservas = reservas;
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

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacion2() {
        return ubicacion2;
    }

    public void setUbicacion2(String ubicacion2) {
        this.ubicacion2 = ubicacion2;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getNormas() {
        return normas;
    }

    public void setNormas(String normas) {
        this.normas = normas;
    }

    public String getSaludSeguridad() {
        return saludSeguridad;
    }

    public void setSaludSeguridad(String saludSeguridad) {
        this.saludSeguridad = saludSeguridad;
    }

    public String getCancelacion() {
        return cancelacion;
    }

    public void setCancelacion(String cancelacion) {
        this.cancelacion = cancelacion;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}

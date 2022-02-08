package com.example.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    private String nombre;
    private String descripcionCorta;

    @Column(columnDefinition = "text")
    private String descripcionLarga;

    private String direccion;

    @Column(columnDefinition = "text", nullable = false)
    private String ubicacion;

    @Column(columnDefinition = "text", nullable = false)
    private String ubicacion2;

    @Column(columnDefinition = "text")
    private String normas;

    @Column(columnDefinition = "text")
    private String saludSeguridad;

    @Column(columnDefinition = "text")
    private String cancelacion;

    private Integer puntuacion;

    private Integer precio;

    @ManyToOne(optional = false)
    @JoinColumn(name="categoria_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name="ciudad_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ciudad ciudad;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="producto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Imagen> imagenes = new HashSet<>();

    @OneToMany(mappedBy = "producto", orphanRemoval = true)
    @JsonIgnore
    private Set<Reserva> reservas = new HashSet<>();

    @JoinTable(
            name = "PRODUCTO_CARACTERISTICA",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name="caracteristica_id")
    )
    @ManyToMany
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    public Producto() {
    }

    public Producto(String nombre, String descripcionCorta, String descripcionLarga, String direccion, String ubicacion,
                    String ubicacion2, String normas, String saludSeguridad, String cancelacion, Integer puntuacion,
                   Integer precio, Categoria categoria, Ciudad ciudad, Set<Imagen> imagenes, Set<Reserva> reservas,
                    Set<Caracteristica> caracteristicas) {
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.ubicacion2 = ubicacion2;
        this.normas = normas;
        this.saludSeguridad = saludSeguridad;
        this.cancelacion = cancelacion;
        this.puntuacion = puntuacion;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.imagenes = imagenes;
        this.reservas = reservas;
        this.caracteristicas = caracteristicas;
        this.precio = precio;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}

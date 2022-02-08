package com.example.digitalBooking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CARACTERISTICAS")
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Long id;
    private String nombre;
    private String icono;

    @ManyToMany(mappedBy = "caracteristicas")
    @JsonIgnore
    private Set<Producto> productos;

    public Caracteristica() {
    }

    public Caracteristica(String nombre, String icono) {
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

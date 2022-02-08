package com.example.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CIUDADES")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Long id;
    private String nombre;
    private String nombre_pais;

    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    Set<Producto> productos;

    public Ciudad() {
    }

    public Ciudad(String nombre, String nombre_pais) {
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

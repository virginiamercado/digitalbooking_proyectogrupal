package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.Rol;
import com.example.digitalBooking.model.Usuario;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;

public class RolDTO extends Rol {

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String nombre;

    @ApiModelProperty(position = 2)
    private Set<Usuario> usuarios;

    public RolDTO() {
    }

    public RolDTO(String nombre) {
        this.nombre = nombre;
        this.usuarios = new HashSet<>();
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

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

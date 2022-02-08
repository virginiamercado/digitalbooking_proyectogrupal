package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.Producto;
import com.example.digitalBooking.model.Reserva;
import com.example.digitalBooking.model.Rol;
import com.example.digitalBooking.model.Usuario;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

public class UsuarioDTO extends Usuario {

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String nombre;

    @ApiModelProperty(position = 2)
    private String apellido;

    @ApiModelProperty(position = 3)
    private String fechaNacimiento;

    @ApiModelProperty(position = 4)
    private String paisNacimiento;

    @ApiModelProperty(position = 5)
    private String email;

    @ApiModelProperty(position = 6)
    private String password;

    @ApiModelProperty(position = 7)
    private Rol rol;

    @ApiModelProperty(position = 8)
    private String username;

    @ApiModelProperty(position = 9)
    private Set<Reserva> reservas;

    @ApiModelProperty(position = 10)
    private Set<Producto> favoritos;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String apellido, String fechaNacimiento, String paisNacimiento, String email,
                      String password, Rol rol, Set<Reserva> reservas, Set<Producto> favoritos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.paisNacimiento = paisNacimiento;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.username = email;
        this.reservas = reservas;
        this.favoritos = favoritos;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Producto> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Producto> favoritos) {
        this.favoritos = favoritos;
    }
}

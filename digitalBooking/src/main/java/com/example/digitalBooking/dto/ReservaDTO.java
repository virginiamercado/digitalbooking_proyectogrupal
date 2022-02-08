package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.Producto;
import com.example.digitalBooking.model.Usuario;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class ReservaDTO {

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String horaLlegada;

    @ApiModelProperty(position = 2)
    private LocalDate fechaLlegada;

    @ApiModelProperty(position = 3)
    private LocalDate fechaPartida;

    @ApiModelProperty(position = 4)
    private Usuario usuario;

    @ApiModelProperty(position = 5)
    private Producto producto;

    public ReservaDTO() {
    }

    public ReservaDTO(String horaLlegada, LocalDate fechaLlegada, LocalDate fechaPartida, Usuario usuario, Producto producto) {
        this.horaLlegada = horaLlegada;
        this.fechaLlegada = fechaLlegada;
        this.fechaPartida = fechaPartida;
        this.usuario = usuario;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(LocalDate fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}

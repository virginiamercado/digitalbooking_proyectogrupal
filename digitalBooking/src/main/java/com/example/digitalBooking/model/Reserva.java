package com.example.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="RESERVAS")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;
    private String horaLlegada;

    @Column(nullable = false)
    private LocalDate fechaLlegada;

    @Column(nullable = false)
    private LocalDate fechaPartida;

    @ManyToOne(optional = false)
    @JoinColumn(name="usuario_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name="producto_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;

    public Reserva() {
    }

    public Reserva(String horaLlegada, LocalDate fechaLlegada, LocalDate fechaPartida, Usuario usuario, Producto producto) {
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

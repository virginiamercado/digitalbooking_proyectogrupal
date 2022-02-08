package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Producto;
import com.example.digitalBooking.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
        @Query("SELECT r FROM Reserva r WHERE r.producto.id = ?1")
        List<Reserva> findReservasByProductoId(Long id);

        @Query("SELECT r FROM Reserva r WHERE r.usuario.id = ?1")
        List<Reserva> findReservasByUsuarioId(Long id);

        @Query("SELECT r FROM Reserva r WHERE r.fechaLlegada = ?1 AND r.fechaPartida = ?2 AND r.producto.id = ?3")
        Optional<Reserva> findByFechasYProducto(LocalDate fechaLlegada, LocalDate fechaPartida, Long idProducto);
}

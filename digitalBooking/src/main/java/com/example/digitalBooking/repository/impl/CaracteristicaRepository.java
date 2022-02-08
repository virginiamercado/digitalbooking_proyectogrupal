package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {

    @Query("SELECT c FROM Caracteristica c WHERE c.nombre = ?1")
    Optional<Caracteristica> findByNombre(String nombre);
}

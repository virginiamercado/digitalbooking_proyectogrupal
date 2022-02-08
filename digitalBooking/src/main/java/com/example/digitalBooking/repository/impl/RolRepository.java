package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Imagen;
import com.example.digitalBooking.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    @Query("SELECT r FROM Rol r WHERE r.nombre = ?1")
    Optional<Rol> findByNombre(String nombre);
}

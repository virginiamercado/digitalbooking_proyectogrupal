package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Categoria;
import com.example.digitalBooking.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    @Query("SELECT c FROM Ciudad c WHERE c.nombre = ?1 AND c.nombre_pais = ?2")
    Optional<Ciudad> findByNombreYPais(String nombre, String pais);

    @Query("SELECT c FROM Ciudad c WHERE c.nombre = '%s%'")
    List<Ciudad> findByNombre(String nombre);
}

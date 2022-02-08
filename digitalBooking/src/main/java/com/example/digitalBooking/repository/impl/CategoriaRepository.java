package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Caracteristica;
import com.example.digitalBooking.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.titulo = ?1")
    Optional<Categoria> findByTitulo(String titulo);

}

package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Ciudad;
import com.example.digitalBooking.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    @Query("SELECT i FROM Imagen i WHERE i.url = ?1")
    Optional<Imagen> findByUrl(String url);
}

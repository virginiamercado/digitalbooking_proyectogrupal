package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Producto;
import com.example.digitalBooking.model.Rol;
import com.example.digitalBooking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //ESTE METODO LO HICIMOS NOSOTRAS
    Usuario findByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u.favoritos FROM Usuario u WHERE u.id = ?1")
    Set<Producto> findFavoritos(Long id);

}

package com.example.digitalBooking.repository.impl;

import com.example.digitalBooking.model.Imagen;
import com.example.digitalBooking.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.categoria.titulo = ?1")
    public List<Producto> findProductosByCategoria(String tituloCategoria);

    @Query("SELECT p FROM Producto p WHERE p.ciudad.nombre = ?1")
    public List<Producto> findProductosByCiudad(String ciudad);

    // busqueda por rango de fechas, trae los productos reservados
    @Query("SELECT p FROM Producto p INNER JOIN Reserva r ON p.id = r.producto.id WHERE ?1 < r.fechaPartida AND ?2 > " +
            "r.fechaLlegada")
    public List<Producto> findProductosReservadosByFechas(LocalDate fechaIngreso, LocalDate fechaSalida);

    // busqueda por rango de fechas y ciudad, trae los productos reservados
    //@Query("SELECT p FROM Producto p INNER JOIN Reserva r ON p.id = r.producto.id INNER JOIN Ciudad c ON c.id = p" +
      //      ".ciudad.id WHERE ?1 < r.fechaPartida AND ?2 > r.fechaLlegada AND c.nombre = ?3")
    @Query(value = "SELECT * FROM productos p INNER JOIN reservas r ON id_producto = producto_id INNER JOIN ciudades c ON " +
            "ciudad_id = id_ciudad WHERE ?1 < r.fecha_partida AND ?2 > fecha_llegada AND c.nombre = ?3", nativeQuery = true)
    public List<Producto> findProductosReservadosByFechasAndCiudad(LocalDate fechaIngreso, LocalDate fechaEgreso,
                                                                   String ciudad);

    @Query("SELECT p FROM Producto p WHERE p.nombre = ?1 AND p.ubicacion = ?2")
    Optional<Producto> findByNombreYUbicacion(String nombre, String ubicacion);

}

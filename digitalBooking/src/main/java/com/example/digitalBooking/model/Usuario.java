package com.example.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USUARIOS")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    private String nombre;

    @Column(unique = true)
    private String username;
    private String apellido;
    private String fechaNacimiento;
    private String paisNacimiento;


    private String email;
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name="rol_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Rol rol;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    @JsonIgnore
    Set<Reserva> reservas;

    @JoinTable(
            name = "USUARIO_PRODUCTO",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name="producto_id")
    )
    @ManyToMany
    private Set<Producto> favoritos = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String fechaNacimiento, String paisNacimiento,
                   String email, String password, Rol rol, Set<Reserva> reservas, Set<Producto> favoritos) {
        this.id = id;
        this.nombre = nombre;
        this.username = email;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.paisNacimiento = paisNacimiento;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.reservas = reservas;
        this.favoritos = favoritos;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Producto> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Producto> favoritos) {
        this.favoritos = favoritos;
    }
/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(getRol().getNombre());
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
*/

}

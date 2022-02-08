package com.example.digitalBooking.service;

import com.example.digitalBooking.dto.ProductoDTO;
import com.example.digitalBooking.dto.UsuarioDTO;
import com.example.digitalBooking.model.Producto;
import com.example.digitalBooking.model.Rol;
import com.example.digitalBooking.model.Usuario;
import com.example.digitalBooking.repository.impl.ProductoRepository;
import com.example.digitalBooking.repository.impl.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.emptyList;

@Service
public class UsuarioService implements IService<UsuarioDTO>{
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private ObjectMapper mapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ProductoRepository productoRepository,
                          ObjectMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuario) throws Exception{
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new Exception("Ya existe un usuario con este email!");
        } else {
            usuarioRepository.save(mapper.convertValue(usuario, Usuario.class));
        }
        return usuario;
    }

    @Override
    public Optional<UsuarioDTO> findById(Long id) {
        UsuarioDTO usuarioDTO = null;
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
        }
        return Optional.ofNullable(usuarioDTO);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario: usuarios) {
            usuariosDTO.add(mapper.convertValue(usuario, UsuarioDTO.class));
        }
        return usuariosDTO;
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDTO) {
        usuarioRepository.save(mapper.convertValue(usuarioDTO, Usuario.class));
        return usuarioDTO;
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioDTO> findUsuarioByEmail(String email) {
        UsuarioDTO usuarioDTO = null;
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
        }
        return Optional.ofNullable(usuarioDTO);
    }

//ESTE METODO TAMBIEN LO HICIMOS NOSOTRAS

    public UsuarioDTO findByUsername (String username) {
       Usuario usuario = usuarioRepository.findByUsername(username);
       return mapper.convertValue(usuario,UsuarioDTO.class);
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
    }


    public UsuarioDTO cambiarRol(String username, Rol rolId) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        usuario.setRol(rolId);
        return mapper.convertValue(usuario, UsuarioDTO.class);
    }

    public List<ProductoDTO> findFavoritos(Long id) {
        Set<Producto> favoritos = usuarioRepository.findFavoritos(id);
        List<ProductoDTO> favoritosDTO = new ArrayList<>();
        for (Producto producto: favoritos) {
            favoritosDTO.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return favoritosDTO;
    }

    public List<ProductoDTO> addFavorito(Long idUsuario, Long idProducto) throws Exception{
        Set<Producto> favoritos = usuarioRepository.findFavoritos(idUsuario);
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()){
            favoritos.add(producto.get());
        } else {
            throw new Exception("Producto no encontrado!");
        }
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()) {
            usuario.get().setFavoritos(favoritos);
            usuarioRepository.save(usuario.get());
        } else {
            throw new Exception("Usuario no encontrado!");
        }
        List<ProductoDTO> favoritosDTO = new ArrayList<>();
        for (Producto prod: favoritos) {
            favoritosDTO.add(mapper.convertValue(prod, ProductoDTO.class));
        }
        return favoritosDTO;
    }

    public List<ProductoDTO> removeFavorito(Long idUsuario, Long idProducto) throws Exception{
        Set<Producto> favoritos = usuarioRepository.findFavoritos(idUsuario);
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()) {
            favoritos.remove(producto.get());
        } else {
            throw new Exception("Producto no encontrado!");
        }
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()) {
            usuario.get().setFavoritos(favoritos);
            usuarioRepository.save(usuario.get());
        }else {
            throw new Exception("Usuario no encontrado!");
        }
        List<ProductoDTO> favoritosDTO = new ArrayList<>();
        for (Producto prod: favoritos) {
            favoritosDTO.add(mapper.convertValue(prod, ProductoDTO.class));
        }
        return favoritosDTO;
    }
}



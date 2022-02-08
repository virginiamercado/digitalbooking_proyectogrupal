package com.example.digitalBooking.controller;

import com.example.digitalBooking.dto.ProductoDTO;
import com.example.digitalBooking.dto.UsuarioDTO;
import com.example.digitalBooking.model.Producto;
import com.example.digitalBooking.model.Rol;
import com.example.digitalBooking.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation(value = "Registro de un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PostMapping("/registro")
    public ResponseEntity saveUsuario(@RequestBody UsuarioDTO usuario) throws Exception {
       usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        return new ResponseEntity(usuarioService.save(usuario), HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Buscar usuario por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("{id}")
    public Optional<UsuarioDTO> findUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @ApiOperation(value = "Listar todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping()
    public List<UsuarioDTO> findAllUsuarios() {
        return usuarioService.findAll();
    }

    @ApiOperation(value = "Modificar un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/modificar")
    public ResponseEntity updateUsuario(@RequestBody UsuarioDTO usuario) {
        return new ResponseEntity(usuarioService.update(usuario), HttpStatus.OK);
    }

    @ApiOperation(value = "Eliminar un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Eliminado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Buscar usuario por email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/emailUser/{email}")
    public Optional<UsuarioDTO> findByEmail(@PathVariable String email) {
        return usuarioService.findUsuarioByEmail(email);
    }

    @ApiOperation(value = "Buscar usuario por username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Encontrado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/findUser/{username}")
    public UsuarioDTO findByUsername(@PathVariable String username) {
        return usuarioService.findByUsername(username);
    }

    @ApiOperation(value = "Cambiar rol a un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modificado con éxito!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/cambiar-rol")
    public ResponseEntity cambiarRol(@RequestBody String username, Rol rolId) {
        return new ResponseEntity(usuarioService.cambiarRol(username, rolId), HttpStatus.OK);
    }

    @ApiOperation(value = "Listar los favoritos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @GetMapping("/favoritos/{id}")
    public List<ProductoDTO> findFavoritos(@PathVariable Long id) {
        return usuarioService.findFavoritos(id);
    }

    @ApiOperation(value = "Agregar un favorito a un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/favoritos/agregar")
    public ResponseEntity addFavorito(@RequestParam Long idUsuario, @RequestParam Long idProducto) throws Exception {
        return new ResponseEntity(usuarioService.addFavorito(idUsuario, idProducto), HttpStatus.OK);
    }

    @ApiOperation(value = "Quitar un favorito a un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
    @PutMapping("/favoritos/quitar")
    public ResponseEntity removeFavorito(@RequestParam Long idUsuario, @RequestParam Long idProducto) throws Exception{
        return new ResponseEntity(usuarioService.removeFavorito(idUsuario, idProducto), HttpStatus.OK);
    }

}

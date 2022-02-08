package com.example.digitalBooking.service;

import com.example.digitalBooking.dto.RolDTO;
import com.example.digitalBooking.model.Rol;
import com.example.digitalBooking.repository.impl.RolRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IService<RolDTO>{
    private RolRepository rolRepository;
    private ObjectMapper mapper;

    @Autowired
    public RolService(RolRepository rolRepository, ObjectMapper mapper) {
        this.rolRepository = rolRepository;
        this.mapper = mapper;
    }

    @Override
    public RolDTO save(RolDTO rol) throws Exception{
        if (rolRepository.findByNombre(rol.getNombre()).isPresent()) {
            throw new Exception("Ya existe un rol con este nombre!");
        } else {
            rolRepository.save(mapper.convertValue(rol, Rol.class));
        }
        return rol;
    }

    @Override
    public Optional<RolDTO> findById(Long id) {
        RolDTO rolDTO = null;
        Optional<Rol> rol = rolRepository.findById(id);
        if (rol.isPresent()) {
            rolDTO = mapper.convertValue(rol, RolDTO.class);
        }
        return Optional.ofNullable(rolDTO);
    }

    @Override
    public List<RolDTO> findAll() {
        List<Rol> roles = rolRepository.findAll();
        List<RolDTO> rolesDTO = new ArrayList<>();
        for (Rol rol: roles) {
            rolesDTO.add(mapper.convertValue(rol, RolDTO.class));
        }
        return rolesDTO;
    }

    @Override
    public RolDTO update(RolDTO rolDTO) {
        rolRepository.save(mapper.convertValue(rolDTO, Rol.class));
        return rolDTO;
    }

    @Override
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

}

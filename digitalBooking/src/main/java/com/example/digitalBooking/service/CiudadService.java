package com.example.digitalBooking.service;

import com.example.digitalBooking.dto.CiudadDTO;
import com.example.digitalBooking.model.Ciudad;
import com.example.digitalBooking.repository.impl.CiudadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.classworlds.ClassWorldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiudadService implements IService<CiudadDTO>{

    private CiudadRepository ciudadRepository;
    private ObjectMapper mapper;

    @Autowired
    public CiudadService(CiudadRepository ciudadRepository, ObjectMapper mapper) {
        this.ciudadRepository = ciudadRepository;
        this.mapper = mapper;
    }

    @Override
    public CiudadDTO save(CiudadDTO ciudad) throws Exception{
        if (ciudadRepository.findByNombreYPais(ciudad.getNombre(), ciudad.getNombre_pais()).isPresent()) {
            throw new Exception("Ya existe una ciudad con este nombre y pais!");
        } else {
            ciudadRepository.save(mapper.convertValue(ciudad, Ciudad.class));
        }
        return ciudad;
    }

    @Override
    public Optional<CiudadDTO> findById(Long id) {
        CiudadDTO ciudadDTO = null;
        Optional<Ciudad> ciudad  = ciudadRepository.findById(id);
        if(ciudad.isPresent()) {
            ciudadDTO = mapper.convertValue(ciudad, CiudadDTO.class);
        }
        return Optional.ofNullable(ciudadDTO);
    }

    @Override
    public List<CiudadDTO> findAll() {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        List<CiudadDTO> ciudadesDTO = new ArrayList<>();
        for(Ciudad ciudad : ciudades) {
            ciudadesDTO.add(mapper.convertValue(ciudad, CiudadDTO.class));
        }
        return ciudadesDTO;
    }

    @Override
    public CiudadDTO update(CiudadDTO ciudad) {
        ciudadRepository.save(mapper.convertValue(ciudad, Ciudad.class));
        return ciudad;
    }

    @Override
    public void delete(Long id) {
        ciudadRepository.deleteById(id);
    }

    public List<CiudadDTO> findCiudadesByNombre(String nombre) {
        List<Ciudad> ciudades = ciudadRepository.findByNombre(nombre);
        List<CiudadDTO> ciudadesDTO = new ArrayList<>();
        for(Ciudad ciudad : ciudades) {
            ciudadesDTO.add(mapper.convertValue(ciudad, CiudadDTO.class));
        }
        return ciudadesDTO;
    }
}

package com.example.digitalBooking.service;

import com.example.digitalBooking.dto.CaracteristicaDTO;
import com.example.digitalBooking.model.Caracteristica;
import com.example.digitalBooking.repository.impl.CaracteristicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicaService implements IService<CaracteristicaDTO> {

    private CaracteristicaRepository caracteristicaRepository;
    private ObjectMapper mapper;

    @Autowired
    public CaracteristicaService(CaracteristicaRepository caracteristicaRepository, ObjectMapper mapper) {
        this.caracteristicaRepository = caracteristicaRepository;
        this.mapper = mapper;
    }

    @Override
    public CaracteristicaDTO save(CaracteristicaDTO caracteristica) throws Exception{
        if(caracteristicaRepository.findByNombre(caracteristica.getNombre()).isPresent()) {
            throw new Exception("Ya existe una caracteristica con este nombre!");
        } else {
            caracteristicaRepository.save(mapper.convertValue(caracteristica, Caracteristica.class));
        }
        return caracteristica;
    }

    @Override
    public Optional<CaracteristicaDTO> findById(Long id) {
        CaracteristicaDTO caracteristicaDTO = null;
        Optional<Caracteristica> caracteristica = caracteristicaRepository.findById(id);
        if(caracteristica.isPresent()) {
            caracteristicaDTO = mapper.convertValue(caracteristica, CaracteristicaDTO.class);
        }
        return Optional.ofNullable(caracteristicaDTO);
    }

    @Override
    public List<CaracteristicaDTO> findAll() {
        List<Caracteristica> caracteristicas = caracteristicaRepository.findAll();
        List<CaracteristicaDTO> caracteristicasDTO = new ArrayList<>();
        for(Caracteristica caracteristica : caracteristicas) {
            caracteristicasDTO.add(mapper.convertValue(caracteristica, CaracteristicaDTO.class));
        }
        return caracteristicasDTO;
    }

    @Override
    public CaracteristicaDTO update(CaracteristicaDTO caracteristicaDTO) {
        caracteristicaRepository.save(mapper.convertValue(caracteristicaDTO, Caracteristica.class));
        return caracteristicaDTO;
    }

    @Override
    public void delete(Long id) {
        caracteristicaRepository.deleteById(id);
    }
}

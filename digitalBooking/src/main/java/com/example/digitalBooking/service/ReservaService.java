package com.example.digitalBooking.service;

import com.example.digitalBooking.dto.ReservaDTO;
import com.example.digitalBooking.model.Reserva;
import com.example.digitalBooking.repository.impl.ReservaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements IService<ReservaDTO>{
    private ReservaRepository reservaRepository;
    private ObjectMapper mapper;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ObjectMapper mapper) {
        this.reservaRepository = reservaRepository;
        this.mapper = mapper;
    }

    @Override
    public ReservaDTO save(ReservaDTO reserva) throws Exception{
        if (reservaRepository.findByFechasYProducto(reserva.getFechaLlegada(), reserva.getFechaPartida(),
                reserva.getProducto().getId()).isPresent()) {
            throw new Exception("Ya existe una reserva para este producto en estas fechas!");
        } else {
            reservaRepository.save(mapper.convertValue(reserva, Reserva.class));
        }
        return reserva;
    }

    @Override
    public Optional<ReservaDTO> findById(Long id) {
        ReservaDTO reservaDTO = null;
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            reservaDTO = mapper.convertValue(reserva, ReservaDTO.class);
        }
        return Optional.ofNullable(reservaDTO);
    }

    @Override
    public List<ReservaDTO> findAll() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva: reservas) {
            reservasDTO.add(mapper.convertValue(reserva, ReservaDTO.class));
        }
        return reservasDTO;
    }

    @Override
    public ReservaDTO update(ReservaDTO reservaDTO) {
        reservaRepository.save(mapper.convertValue(reservaDTO, Reserva.class));
        return reservaDTO;
    }

    @Override
    public void delete(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<ReservaDTO> findReservasByProductoId(Long id){
        List<Reserva> reservas = reservaRepository.findReservasByProductoId(id);
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva: reservas) {
            reservasDTO.add(mapper.convertValue(reserva, ReservaDTO.class));
        }
        return reservasDTO;
    }

    public List<ReservaDTO> findReservasByUsuarioId(Long id){
        List<Reserva> reservas = reservaRepository.findReservasByUsuarioId(id);
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva: reservas) {
            reservasDTO.add(mapper.convertValue(reserva, ReservaDTO.class));
        }
        return reservasDTO;
    }
}

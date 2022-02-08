package com.example.digitalBooking.service;

import com.example.digitalBooking.model.*;
import com.example.digitalBooking.dto.*;
import com.example.digitalBooking.repository.impl.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements IService<CategoriaDTO>{

    private CategoriaRepository categoriaRepository;
    private ObjectMapper mapper;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, ObjectMapper mapper) {
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoriaDTO save(CategoriaDTO categoria) throws Exception{
        if (categoriaRepository.findByTitulo(categoria.getTitulo()).isPresent()) {
            throw new Exception("Ya existe una categoria con este titulo!");
        } else {
            categoriaRepository.save(mapper.convertValue(categoria, Categoria.class));
        }
        return categoria;
    }

    @Override
    public Optional<CategoriaDTO> findById(Long id) {
        CategoriaDTO categoriaDTO = null;
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()) {
            categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);
        }
        return Optional.ofNullable(categoriaDTO);
    }

    @Override
    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();
        for(Categoria cat : categorias) {
            categoriasDTO.add(mapper.convertValue(cat, CategoriaDTO.class));
        }
        return categoriasDTO;
    }

    @Override
    public CategoriaDTO update(CategoriaDTO categoria) {
        categoriaRepository.save(mapper.convertValue(categoria, Categoria.class));
        return categoria;
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}

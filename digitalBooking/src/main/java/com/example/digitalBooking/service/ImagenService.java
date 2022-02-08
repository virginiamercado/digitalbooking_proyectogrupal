package com.example.digitalBooking.service;

import com.example.digitalBooking.dto.ImagenDTO;
import com.example.digitalBooking.model.Imagen;
import com.example.digitalBooking.repository.impl.ImagenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService implements IService<ImagenDTO>{

    private ImagenRepository imagenRepository;
    private ObjectMapper mapper;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository, ObjectMapper mapper) {
        this.imagenRepository = imagenRepository;
        this.mapper = mapper;
    }

    @Override
    public ImagenDTO save(ImagenDTO imagen) throws Exception{
        if (imagenRepository.findByUrl(imagen.getUrl()).isPresent()) {
            throw new Exception("Ya existe una imagen con esta url!");
        } else {
            imagenRepository.save(mapper.convertValue(imagen, Imagen.class));
        }
        return imagen;
    }

    @Override
    public Optional<ImagenDTO> findById(Long id) {
        ImagenDTO imagenDTO = null;
        Optional<Imagen> imagen = imagenRepository.findById(id);
        if(imagen.isPresent()) {
            imagenDTO = mapper.convertValue(imagen, ImagenDTO.class);
        }
        return Optional.ofNullable(imagenDTO);
    }

    @Override
    public List<ImagenDTO> findAll() {
        List<Imagen> imagenes = imagenRepository.findAll();
        List<ImagenDTO> imagenesDTO = new ArrayList<>();
        for(Imagen img : imagenes) {
            imagenesDTO.add(mapper.convertValue(img, ImagenDTO.class));
        }
        return imagenesDTO;
    }

    @Override
    public ImagenDTO update(ImagenDTO imagenDTO) {
        imagenRepository.save(mapper.convertValue(imagenDTO, Imagen.class));
        return imagenDTO;
    }

    @Override
    public void delete(Long id) {
        imagenRepository.deleteById(id);
    }
}

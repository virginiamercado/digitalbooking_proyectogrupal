package com.example.digitalBooking.dto;

import com.example.digitalBooking.model.Producto;
import io.swagger.annotations.ApiModelProperty;

public class ImagenDTO {
    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private String titulo;

    @ApiModelProperty(position = 2)
    private String url;

    public ImagenDTO() {
    }

    public ImagenDTO(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

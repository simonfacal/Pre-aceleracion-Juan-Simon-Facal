package com.alkemy.disney.disney.mapper;



import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto)
    {
        PeliculaEntity peliculaEntity=new PeliculaEntity();

        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setFechaCreacion(dto.getFechaCreacion());
        peliculaEntity.setPersonajes(dto.getPersonajes());

        return peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity,boolean loadPersonajes)
    {
        PeliculaDTO peliculaDTO=new PeliculaDTO();

        peliculaDTO.setId(entity.getId());
        peliculaDTO.setCalificacion(entity.getCalificacion());
        peliculaDTO.setTitulo(entity.getTitulo());
        peliculaDTO.setImagen(entity.getImagen());
        peliculaDTO.setFechaCreacion(entity.getFechaCreacion());

        peliculaDTO.setPersonajes(entity.getPersonajes());

        return peliculaDTO;
    }

    public List<PeliculaDTO> peliculaEntitySet2DTOList(List<PeliculaEntity> entities)
    {
        List<PeliculaDTO> dtos=new ArrayList<>();

        return dtos;
    }

}

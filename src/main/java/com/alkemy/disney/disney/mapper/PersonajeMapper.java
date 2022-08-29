package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {


    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto)
    {
        PersonajeEntity personajeEntity=new PersonajeEntity();

        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setPeliculas(dto.getPeliculas());

        return personajeEntity;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity) {
        PersonajeDTO personajeDTO = new PersonajeDTO();

        personajeDTO.setId(entity.getId());
        personajeDTO.setEdad(entity.getEdad());
        personajeDTO.setHistoria(entity.getHistoria());
        personajeDTO.setNombre(entity.getNombre());
        personajeDTO.setImagen(entity.getImagen());
        personajeDTO.setPeso(entity.getPeso());
        personajeDTO.setPeliculas(entity.getPeliculas());

        return personajeDTO;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> entities)
    {
        List<PersonajeDTO> dtos=new ArrayList<>();
        for (PersonajeEntity entity: entities)
        {
            dtos.add(this.personajeEntity2DTO(entity));
        }
        return dtos;
    }


}

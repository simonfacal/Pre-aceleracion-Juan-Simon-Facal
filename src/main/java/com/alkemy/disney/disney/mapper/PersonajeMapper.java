package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto)
    {
        PersonajeEntity personajeEntity=new PersonajeEntity();

        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setPeso(dto.getPeso());

        return personajeEntity;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPeliculas) {
        PersonajeDTO personajeDTO = new PersonajeDTO();

        personajeDTO.setId(entity.getId());
        personajeDTO.setEdad(entity.getEdad());
        personajeDTO.setHistoria(entity.getHistoria());
        personajeDTO.setNombre(entity.getNombre());
        personajeDTO.setImagen(entity.getImagen());
        personajeDTO.setPeso(entity.getPeso());
        if(loadPeliculas)
        {
            List<PeliculaDTO> peliculaDTOS=this.peliculaMapper.peliculaEntityList2DTOList(entity.getPeliculas(),false);
            personajeDTO.setPeliculas(peliculaDTOS);
        }
        return personajeDTO;
    }



    public void personajeEntityRefreshValues(PersonajeEntity entity,PersonajeDTO personajeDTO)
    {
        entity.setEdad(personajeDTO.getEdad());
        entity.setHistoria(personajeDTO.getHistoria());
        entity.setNombre(personajeDTO.getNombre());
        entity.setPeso(personajeDTO.getPeso());
        entity.setImagen(personajeDTO.getImagen());
    }


    public Set<PersonajeEntity> personajeDTOList2Entity(List<PersonajeDTO>dtos)
    {
        Set<PersonajeEntity> entities=new HashSet<>();
        for (PersonajeDTO dto: dtos)
        {
            entities.add(this.personajeDTO2Entity(dto));
        }
        return entities;
    }

    public List<PersonajeDTO> personajeEntitySet2DTOList(Collection<PersonajeEntity> entities, boolean loadPeliculas)
    {
        List<PersonajeDTO> dtos=new ArrayList<>();
        for (PersonajeEntity entity: entities)
        {
            dtos.add(this.personajeEntity2DTO(entity,loadPeliculas));
        }
        return dtos;
    }



    public List<PersonajeBasicDTO> personajeEntitySet2BasicDTOList(Collection<PersonajeEntity> entities)
    {
        List<PersonajeBasicDTO> dtos=new ArrayList<>();
        PersonajeBasicDTO basicDTO;
        for (PersonajeEntity entity: entities)
        {
            basicDTO=new PersonajeBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setNombre(entity.getNombre());
            dtos.add(basicDTO);


        }
        return dtos;
    }


}

package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    public GeneroEntity generoDTO2Entity(GeneroDTO dto)
    {
        GeneroEntity generoEntity=new GeneroEntity();

        generoEntity.setImagen(dto.getImagen());
        generoEntity.setNombre(dto.getNombre());


        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity)
    {
        GeneroDTO generoDTO=new GeneroDTO();

        generoDTO.setId(entity.getId());
        generoDTO.setImagen(entity.getImagen());
        generoDTO.setNombre(entity.getNombre());


        return generoDTO;
    }

    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> entities)
    {
        List<GeneroDTO> dtos=new ArrayList<>();
        for (GeneroEntity entity: entities)
        {
            dtos.add(this.generoEntity2DTO(entity));
        }

        return dtos;
    }

}

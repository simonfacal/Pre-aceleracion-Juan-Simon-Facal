package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity generoDTO2Entity(GenreDTO dto)
    {
        GenreEntity genreEntity =new GenreEntity();

        genreEntity.setImage(dto.getImage());
        genreEntity.setName(dto.getName());


        return genreEntity;
    }

    public GenreDTO generoEntity2DTO(GenreEntity entity)
    {
        GenreDTO genreDTO =new GenreDTO();

        genreDTO.setId(entity.getId());
        genreDTO.setImage(entity.getImage());
        genreDTO.setName(entity.getName());


        return genreDTO;
    }

    public List<GenreDTO> generoEntityList2DTOList(List<GenreEntity> entities)
    {
        List<GenreDTO> dtos=new ArrayList<>();
        for (GenreEntity entity: entities)
        {
            dtos.add(this.generoEntity2DTO(entity));
        }
        return dtos;
    }

}

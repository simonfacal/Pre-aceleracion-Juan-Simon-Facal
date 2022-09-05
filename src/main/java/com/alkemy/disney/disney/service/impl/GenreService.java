package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.IGenreRepository;
import com.alkemy.disney.disney.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements IGenreService {
    @Autowired
    private IGenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;

    public GenreService() {
    }

    @Override
    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity= genreMapper.generoDTO2Entity(dto);
        GenreEntity entitySaved= genreRepository.save(entity);
        GenreDTO result= genreMapper.generoEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities=this.genreRepository.findAll();
        List<GenreDTO> result=this.genreMapper.generoEntityList2DTOList(entities);
        return result;
    }
}

package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.IGeneroRepository;
import com.alkemy.disney.disney.service.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService implements IGeneroService {
    @Autowired
    private IGeneroRepository generoRepository;
    @Autowired
    private GeneroMapper generoMapper;

    public GeneroService() {
    }

    @Override
    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity entity=generoMapper.generoDTO2Entity(dto);
        GeneroEntity entitySaved=generoRepository.save(entity);
        GeneroDTO result=generoMapper.generoEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> entities=this.generoRepository.findAll();
        List<GeneroDTO> result=this.generoMapper.generoEntityList2DTOList(entities);
        return result;
    }
}

package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.IMovieRepository;
import com.alkemy.disney.disney.repository.ICharacterRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterService implements ICharacterService {
    @Autowired
    private ICharacterRepository characterRepository;
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterSpecification characterSpecification;

    @Override
    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity= characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved=characterRepository.save(entity);
        CharacterDTO result= characterMapper.characterEntity2DTO(entitySaved,true);
        return result;
    }



    @Override
    public void delete(Long id) {
        if (!characterRepository.existsById(id))
            throw new ParamNotFound("Id character not valid");
        characterRepository.deleteById(id);
    }

    @Override
    public CharacterDTO update(Long id, CharacterDTO dto) {

        Optional<CharacterEntity> character=characterRepository.findById(id);
        if (!character.isPresent())
            throw new ParamNotFound("Id character not valid");
        CharacterEntity characterSaved=characterMapper.characterDTO2Entity(dto);
        characterSaved.setId(id);
        characterRepository.save(characterSaved);
        CharacterDTO result= characterMapper.characterEntity2DTO(characterSaved,true);
        return result;

    }

    @Override
    public CharacterDTO getDetailsById(Long id) {
        Optional<CharacterEntity> character=characterRepository.findById(id);
        if (!character.isPresent())
            throw new ParamNotFound("Id character not valid");
        CharacterDTO result= characterMapper.characterEntity2DTO(character.get(),true);
        return result;
    }

    @Override
    public List<CharacterBasicDTO> getByFilters(String name, Integer age, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO=new CharacterFiltersDTO(name,age,movies);
        List<CharacterEntity>entities=characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterBasicDTO> dtos= characterMapper.characterEntitySet2BasicDTOList(entities);
        return dtos;

    }


}

package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface ICharacterService {

    public CharacterDTO save(CharacterDTO dto);
    public void delete(Long id);
    public CharacterDTO update(Long id, CharacterDTO dto);
    public List<CharacterBasicDTO> getAll();
    public CharacterDTO getDetailsById(Long id);
    public List<CharacterBasicDTO> getByFilters(String name, Integer age, Set<Long>movies);

}

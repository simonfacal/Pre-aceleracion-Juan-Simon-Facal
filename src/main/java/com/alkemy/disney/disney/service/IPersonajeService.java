package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;

import java.util.List;
import java.util.Set;

public interface IPersonajeService {

    public PersonajeDTO save(PersonajeDTO dto);
    public void delete(Long id);
    public PersonajeDTO update(Long id,PersonajeDTO dto);
    public List<PersonajeBasicDTO> getAll();
    public PersonajeDTO getDetailsById(Long id);
    public List<PersonajeBasicDTO> getByFilters(String name, int age, Set<Long>movies);

}

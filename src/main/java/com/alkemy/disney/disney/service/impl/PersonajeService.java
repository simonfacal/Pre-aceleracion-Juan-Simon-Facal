package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeFiltersDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.IPeliculaRepository;
import com.alkemy.disney.disney.repository.IPersonajeRepository;
import com.alkemy.disney.disney.repository.specifications.PersonajeSpecification;
import com.alkemy.disney.disney.service.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeService implements IPersonajeService {
    @Autowired
    private IPersonajeRepository personajeRepository;
    @Autowired
    private IPeliculaRepository peliculaRepository;
    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeSpecification personajeSpecification;

    @Override
    public PersonajeDTO save(PersonajeDTO dto) {
        PersonajeEntity entity=personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved=personajeRepository.save(entity);
        PersonajeDTO result=personajeMapper.personajeEntity2DTO(entitySaved,true);
        return result;
    }

    @Override
    public List<PersonajeBasicDTO> getAll() {
        List<PersonajeEntity> entities=personajeRepository.findAll();
        List<PersonajeBasicDTO>result=personajeMapper.personajeEntitySet2BasicDTOList(entities);
        return result;
    }

    @Override
    public void delete(Long id) {

        personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO update(Long id, PersonajeDTO dto) {

        Optional<PersonajeEntity> personaje=personajeRepository.findById(id);
        if (!personaje.isPresent())
            throw new ParamNotFound("Id personaje no valido");
        PersonajeEntity personajeGuardado=personaje.get();
        personajeGuardado.setImagen(dto.getImagen());
        personajeGuardado.setPeso(dto.getPeso());
        personajeGuardado.setEdad(dto.getEdad());
        personajeGuardado.setNombre(dto.getNombre());
        personajeGuardado.setHistoria(dto.getHistoria());
        personajeRepository.save(personajeGuardado);
        PersonajeDTO result=personajeMapper.personajeEntity2DTO(personajeGuardado,true);
        return result;

    }

    @Override
    public PersonajeDTO getDetailsById(Long id) {
        Optional<PersonajeEntity> personaje=personajeRepository.findById(id);
        if (!personaje.isPresent())
            throw new ParamNotFound("Id personaje no valido");
        PersonajeDTO result=personajeMapper.personajeEntity2DTO(personaje.get(),true);
        return result;
    }

    @Override
    public List<PersonajeBasicDTO> getByFilters(String name, int age, Set<Long> movies) {
        PersonajeFiltersDTO filtersDTO=new PersonajeFiltersDTO(name,age,movies);
        List<PersonajeEntity>entities=personajeRepository.findAll(personajeSpecification.getByFilters(filtersDTO));
        List<PersonajeBasicDTO> dtos=personajeMapper.personajeEntitySet2BasicDTOList(entities);
        return dtos;

    }


}

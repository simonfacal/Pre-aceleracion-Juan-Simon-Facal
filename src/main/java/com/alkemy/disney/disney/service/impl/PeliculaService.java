package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaFiltersDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.IPeliculaRepository;
import com.alkemy.disney.disney.repository.IPersonajeRepository;
import com.alkemy.disney.disney.repository.specifications.PeliculaSpecification;
import com.alkemy.disney.disney.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService implements IPeliculaService {
    @Autowired
    private IPeliculaRepository peliculaRepository;
    @Autowired
    private IPersonajeRepository personajeRepository;

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaSpecification peliculaSpecification;

    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity=peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved=peliculaRepository.save(entity);
        PeliculaDTO result=peliculaMapper.peliculaEntity2DTO(entitySaved,true);
        return result;
    }
    @Override
    public PeliculaDTO update(Long id, PeliculaDTO dto) {
        Optional<PeliculaEntity> pelicula = peliculaRepository.findById(id);
        if (!pelicula.isPresent())
            throw new ParamNotFound("Id pelicula no valido");
        PeliculaEntity peliculaGuardada = pelicula.get();
        if(dto.getGenero()!=null)
            peliculaGuardada.setGenero(generoMapper.generoDTO2Entity(dto.getGenero()));
        peliculaGuardada.setCalificacion(dto.getCalificacion());
        peliculaGuardada.setTitulo(dto.getTitulo());
        peliculaGuardada.setImagen(dto.getImagen());
        peliculaGuardada.setFechaCreacion(dto.getFechaCreacion());
        peliculaGuardada.setGeneroId(dto.getGeneroId());
        peliculaRepository.save(peliculaGuardada);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(peliculaGuardada, true);
        return result;
    }
    @Override
    public void delete(Long id) {

        peliculaRepository.deleteById(id);
    }

    @Override
    public List<PeliculaBasicDTO> getAll() {
        List<PeliculaEntity>entities=peliculaRepository.findAll();
        List<PeliculaBasicDTO>result=peliculaMapper.peliculaEntityList2BasicDTOList(entities);

        return result;
    }
    @Override
    public PeliculaDTO getDetailsById(Long id) {
        Optional<PeliculaEntity> pelicula=peliculaRepository.findById(id);
        if(!pelicula.isPresent())
            throw new ParamNotFound("Id pelicula no valido");
        PeliculaDTO result=peliculaMapper.peliculaEntity2DTO(pelicula.get(),true);
        return result;
    }

    @Override
    public List<PeliculaBasicDTO> getByFilters(String name, Long genre, String order) {

        PeliculaFiltersDTO filtersDTO=new PeliculaFiltersDTO(name,genre,order);
        List<PeliculaEntity> entities=peliculaRepository.findAll(peliculaSpecification.getByFilters(filtersDTO));
        List<PeliculaBasicDTO>dtos=peliculaMapper.peliculaEntityList2BasicDTOList(entities);
        return dtos;

    }

    @Override
    public void addCharacter(Long idMovie, Long idCharacter) {
        Optional<PeliculaEntity> pelicula=peliculaRepository.findById(idMovie);
        Optional<PersonajeEntity> personaje=personajeRepository.findById(idCharacter);
        if (!pelicula.isPresent() && !personaje.isPresent())
            throw new ParamNotFound("Id pelicula e Id personaje no validos");
        else
            if(!pelicula.isPresent())
                throw new ParamNotFound("Id pelicula no valido");
            else
                if(!personaje.isPresent())
                    throw new ParamNotFound("Id personaje no valido");
           pelicula.get().getPersonajes().add(personaje.get());
        peliculaRepository.save(pelicula.get());
    }

    @Override
    public void removeCharacter(Long idMovie, Long idCharacter) {
        Optional<PeliculaEntity> pelicula=peliculaRepository.findById(idMovie);
        Optional<PersonajeEntity> personaje=personajeRepository.findById(idCharacter);
        if (!pelicula.isPresent() && !personaje.isPresent())
            throw new ParamNotFound("Id pelicula e Id personaje no validos");
        else
            if(!pelicula.isPresent())
                throw new ParamNotFound("Id pelicula no valido");
            else
                if(!personaje.isPresent())
                    throw new ParamNotFound("Id personaje no valido");
        pelicula.get().getPersonajes().remove(personaje.get());
        peliculaRepository.save(pelicula.get());

    }
}

package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;

import java.util.List;

public interface IPeliculaService {

    public PeliculaDTO save(PeliculaDTO dto);
    public void delete(Long id);
    public PeliculaDTO update(Long id,PeliculaDTO dto);

    public List<PeliculaBasicDTO> getAll();

    public PeliculaDTO getDetailsById(Long id);

    public List<PeliculaBasicDTO> getByFilters(String name,Long genre,String order);

    public void addCharacter(Long idMovie, Long idCharacter);
    public void removeCharacter(Long idMovie, Long idCharacter);
}

package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface IMovieService {

    public MovieDTO save(MovieDTO dto);
    public void delete(Long id);
    public MovieDTO update(Long id, MovieDTO dto);

    public List<MovieBasicDTO> getAll();

    public MovieDTO getDetailsById(Long id);

    public List<MovieBasicDTO> getByFilters(String name, Long genre, String order);

    public void addCharacter(Long idMovie, Long idCharacter);
    public void removeCharacter(Long idMovie, Long idCharacter);
}
